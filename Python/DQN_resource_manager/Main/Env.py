import numpy as np
import pickle
import math

class Env():

  def __init__(self, cue_num=30, vue_num=30, p_num=4, l_num=4, url='./env_2021_1_6'):

    self.vue_num = vue_num # VUE数量
    self.cue_num = cue_num # CUE数量
    self.p_num = p_num  # 功率
    self.l_num = l_num  # MCS模式

    self.SLOT_LIMIT = 100  # 最大容忍时隙
    self.LAMBDA_D = 2 # 权重
    self.N0 = 3.98 * 10 ** 12 # 平均噪声功率
    self.pc = 3.98 # CUE传输功率
    self.p_state = [4, 16, 63] # VUE发射功率
    self.model_ls = [     # MCS模式
                    {'a':5.521,   'b':1.521,  'r':1.123,  'r_idea':120},
                    {'a':16.7,    'b':0.635,  'r':4.428,  'r_idea':280},
                    {'a':15.12,   'b':0.121,  'r':22.428, 'r_idea':502}
                    ]
                    # {'a':5.521,   'b':1.521,  'r':0.505,  'r_idea':120},
                    # {'a':16.7,    'b':0.635,  'r':6.462,  'r_idea':280},
                    # {'a':15.12,   'b':0.121,  'r':13.508, 'r_idea':502}
                    # {'a':4.194,  'b':3.133,  'r':-3.395, 'r_idea':56},
                    # {'a':8.130,   'b':0.947,  'r':3.419,  'r_idea':208},
                    # {'a':12.7,    'b':0.296,  'r':9.332,  'r_idea':408}
    self.V2Vchannels = V2Vchannels()
    self.V2Ichannels = V2Ichannels()

    v_features_num = cue_num * p_num * l_num
    actions_map = np.empty([np.power(v_features_num, vue_num), vue_num * 3], dtype=np.int)
    map_tmp = -1
    for v in range(vue_num):
      index = 0
      diff = np.power(v_features_num, v)
      for p in range(p_num):
        for s in range(cue_num):
          for l in range(l_num):
            actions_map[index:index + diff, v * 3:v * 3 + 3] = [self.p_state[p], s, l]
            actions_map[index:index + diff, :v * 3] = map_tmp
            index += diff
      map_tmp = actions_map[:index, :v * 3 + 3]

    actions_map_sort = np.empty([np.power(v_features_num, vue_num), vue_num * 3], dtype=np.int)
    index = 0
    for i in range(3):
      for j in range(vue_num):
        actions_map_sort[:, index] = actions_map[:, j * 3 + i]
        index += 1
    self.actions_map = actions_map_sort

  def reset(self, vehicles):
    channel_gain_v2v = np.zeros(self.vue_num) # V2V信道增益 h_k_i_t
    channel_gain_v2c = np.zeros(self.vue_num) # V2C信道增益 g_i_k_t
    channel_gain_c2i = np.zeros(self.cue_num) # C2I信道增益 h_i_t
    channel_gain_v2i = np.zeros(self.vue_num) # V2I信道增益 g_k_i_t

    for k in range(self.vue_num):
      vehicle = vehicles[k]
      v2v_if = self.get_V2V(vehicle)
      v2i_if = self.get_V2I(vehicle)
      channel_gain_v2v[k] = v2v_if
      channel_gain_v2i[k] = v2i_if

    for i in range(self.cue_num):
      c2i_if = self.get_C2I(vehicles[i])
      channel_gain_c2i[i] = c2i_if

    package_cur_re_af_trans = np.zeros(self.vue_num)  # package_cur_remain_after_trans
    data_size_remains = np.zeros(self.vue_num)
    for k in range(self.vue_num):
      package_cur_re_af_trans[k] = vehicles[k].data[0][1]
      data_size_remains[k] = np.sum(vehicles[k].data, axis=0)[1]

    state = np.r_[channel_gain_v2v, channel_gain_v2i, channel_gain_v2c, channel_gain_c2i, package_cur_re_af_trans, data_size_remains]
    return state

  def update_state(self, slot_cur, vehicles, action): #action: p, s, l
    ps = self.actions_map[action, :self.vue_num]  # p
    ss = self.actions_map[action, self.vue_num:-self.vue_num]  # s
    ls = self.actions_map[action, -self.vue_num:] # l
    
    channel_gain_v2v = np.zeros(self.vue_num) # V2V信道增益 h_k_i_t
    channel_gain_v2c = np.zeros(self.vue_num) # V2C信道增益 g_i_k_t
    channel_gain_c2i = np.zeros(self.cue_num) # C2I信道增益 h_i_t
    channel_gain_v2i = np.zeros(self.vue_num) # V2I信道增益 g_k_i_t

    rs = [] # the SINR of VUE
    for k in range(self.vue_num):
      s = ss[k]
      vehicle = vehicles[k]
      v2v_if = self.get_V2V(vehicle)
      v2c_if = self.get_V2C(vehicle, vehicles[s])
      channel_gain_v2v[k] = v2v_if
      channel_gain_v2c[k] = v2c_if
      r_ = ps[k] * np.power(np.abs(v2v_if),2) / (self.N0 + self.pc * np.power(np.abs(v2c_if), 2))
      rs.append(r_)

    r_Is = [-1] * self.cue_num  # the SINR of CUE
    for k in range(self.vue_num):
      i = ss[k]
      v2i_if = self.get_V2I(vehicles[k])
      c2i_if = self.get_C2I(vehicles[i])
      channel_gain_v2i[k] = v2i_if
      channel_gain_c2i[k] = c2i_if
      r_Is[i] = self.pc * np.power(np.abs(c2i_if), 2) / (self.N0 + ps[k] * np.power(np.abs(v2i_if), 2))
    for i in range(self.cue_num):
      if r_Is[i] == -1:
        vehicle = vehicles[i]
        c2i_if = self.get_C2I(vehicle)
        channel_gain_c2i[k] = c2i_if
        r_Is[i] = self.pc * np.power(np.abs(c2i_if), 2) / self.N0

    betas = [1] * self.vue_num  # VUE误码率
    r_ks = [0] * self.vue_num # VUE传输速率
    for k in range(self.vue_num):
      l = ls[k]
      if rs[k] >= self.model_ls[l]['r']:
        betas[k] = self.model_ls[l]['a'] * np.power(np.e, - self.model_ls[l]['b'] * rs[k])
      r_ks[k] = self.model_ls[l]['r_idea'] * (1 - betas[k])

    # return
    for k in range(self.vue_num):
      vehicles[k].trans_data(r_ks[k])

    end = False

    reward = np.sum(r_Is) + self.LAMBDA_D * (self.vue_num - np.sum(betas))
    for vehicle in vehicles:
      if len(vehicle.data) > 0 and slot_cur - vehicle.data[0][0] > self.SLOT_LIMIT:
        end = True
        print('data_remain:',vehicle.data)
        print('vehicle:',vehicle.position)
        reward = -1

    package_cur_re_af_trans = np.zeros(self.vue_num)  # package_cur_remain_after_trans
    data_size_remains = np.zeros(self.vue_num)
    for k in range(self.vue_num):
      package_cur_re_af_trans[k] = vehicles[k].data[0][1]
      data_size_remains[k] = np.sum(vehicles[k].data, axis=0)[1]

    state = np.r_[channel_gain_v2v, channel_gain_v2i, channel_gain_v2c, channel_gain_c2i, package_cur_re_af_trans, data_size_remains]
    return state, reward, end


  def get_V2V(self, vehicle):
    return self.V2Vchannels.get_path_loss(vehicle.distance_v2v)

  def get_V2I(self, vehicle):
    return self.V2Ichannels.get_path_loss(vehicle.position)

  def get_V2C(self, vehicle_a, vehicle_b):
    return self.V2Vchannels.get_path_loss(np.abs(vehicle_a.position - vehicle_b.position))

  def get_C2I(self, vehicle):
    return self.V2Ichannels.get_path_loss(vehicle.position)

class V2V_Vehicle:
  
  def __init__(self, start_position, velocity_range, distance_range, arrival_rate, package_avg_size, slot_time):
    self.position = start_position  # 车辆起始位置 m
    self.velocity_range = velocity_range # 车辆速度范围 km/h
    self.velocity = np.random.randint(velocity_range[0], velocity_range[1])/3.6 # 车辆速度 m/s 
    self.distance_range = distance_range  # V2V车辆距离范围 m
    self.distance_v2v = np.random.randint(distance_range[0], distance_range[1]) # # V2V车辆距离 m
    self.arrival_rate = arrival_rate #平均任务到达率
    self.package_avg_size = package_avg_size  # 平均任务大小 bits/packet
    self.data = []
    self.data.append([0,np.random.poisson(self.arrival_rate)])
    self.slot_time = slot_time # 每时隙时间间隔 s

  def trans_data(self, data_trans):
    package_size = 0
    for i in range(len(self.data)):
      package_size += self.data[i][1]
      if package_size > data_trans:
        self.data[i][1] = package_size - data_trans
        break
    self.data = self.data[i:]

  def run(self, slot_cur):
    self.position += self.velocity * self.slot_time
    self.distance_v2v = np.random.randint(self.distance_range[0], self.distance_range[1])
    data_arrive = np.random.poisson(self.arrival_rate)
    self.data.append([slot_cur,data_arrive * self.package_avg_size])

class V2Vchannels:

  def __init__(self):
    self.t = 0
    self.h_bs = 1.5
    self.h_ms = 1.5
    self.fc = 2
    self.decorrelation_distance = 10
    self.shadow_std = 3

  def get_path_loss(self, distance_v2v):
    d = distance_v2v + 0.001
    d_bp = 4 * (self.h_bs - 1) * (self.h_ms - 1) * self.fc * (10 ** 9) / (3 * 10 ** 8)

    def PL_Los(d):
      if d <= 3:
        return 22.7 * np.log10(3) + 41 + 20 * np.log10(self.fc / 5)
      else:
        if d < d_bp:
          return 22.7 * np.log10(d) + 41 + 20 * np.log10(self.fc / 5)
        else:
          return 40.0 * np.log10(d) + 9.45 - 17.3 * np.log10(self.h_bs) - 17.3 * np.log10(self.h_ms) + 2.7 * np.log10(self.fc / 5)

    # def PL_NLos(distance_v2v):
    #     n_j = max(2.8 - 0.0024 * distance_v2v/2, 1.84)
    #     return PL_Los(distance_v2v/2) + 20 - 12.5 * n_j + 10 * n_j * np.log10(distance_v2v/2) + 3 * np.log10(self.fc / 5)

    PL = PL_Los(d)
    # if distance_v2v/2 < 7:
    #     PL = PL_Los(d)
    # else:
    #     PL = min(PL_NLos(distance_v2v), PL_NLos(distance_v2v))
    return PL  # + self.shadow_std * np.random.normal()

  def get_shadowing(self, delta_distance, shadowing):
    return np.exp(-1 * (delta_distance / self.decorrelation_distance)) * shadowing \
            + math.sqrt(1 - np.exp(-2 * (delta_distance / self.decorrelation_distance))) * np.random.normal(0, 3)  # standard dev is 3 db

class V2Ichannels:

  # Simulator of the V2I channels

  def __init__(self):
    self.h_bs = 25
    self.h_ms = 1.5
    self.Decorrelation_distance = 50
    self.BS_position = [100 / 2, 200 / 2]  # center of the grids
    self.shadow_std = 8

  def get_path_loss(self, position):
    d1 = self.BS_position[0]
    d2 = abs(position - self.BS_position[1])
    distance = math.hypot(d1, d2)
    return 128.1 + 37.6 * np.log10(math.sqrt(distance ** 2 + (self.h_bs - self.h_ms) ** 2) / 1000) # + self.shadow_std * np.random.normal()
