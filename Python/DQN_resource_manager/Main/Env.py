import numpy as np
import pickle
import math

class Env():

  lambda_d = 2
  slot_cur = 0
  SLOT_LIMIT = 100

  def __init__(self, cue_num=50, vue_num=50, url='./env_2021_1_6'):
    self.vue_num = vue_num
    self.cue_num = cue_num
    self.N0 =1
    self.pc = 1
    self.V2Vchannels = V2Vchannels()
    self.V2Ichannels = V2Ichannels()
    self.model_ls = [{'a':4.194,  'b':3.133,  'r':-3.395, 'r_idea':56},
                    {'a':5.521,   'b':1.521,  'r':0.505,  'r_idea':120},
                    {'a':8.130,   'b':0.947,  'r':3.419,  'r_idea':208},
                    {'a':16.7,    'b':0.635,  'r':6.462,  'r_idea':280},
                    {'a':12.7,    'b':0.296,  'r':9.332,  'r_idea':408},
                    {'a':15.12,   'b':0.121,  'r':13.508, 'r_idea':502}]

  def update_state(self, vehicles, action): #action: p, s, l
    self.slot_cur += 1
    ps = action['p']
    ss = action['s']
    ls = action['l']

    interfere_v2v = np.zeros(self.vue_num)
    interfere_v2c = np.zeros(self.vue_num)
    interfere_c2i = np.zeros(self.cue_num)
    interfere_v2i = np.zeros(self.vue_num)

    rs = []
    for k in range(self.vue_num):
      vehicle = vehicles[k]
      v2v_if = self.get_V2V(vehicle)
      v2c_if = self.get_V2C(k, self.cue_num)
      interfere_v2v[k] = v2v_if
      interfere_v2c[k] = v2c_if
      s = ss[k]
      r_ = ps[k] * np.power(np.abs(v2v_if),2) / (self.N0 + self.pc * np.power(np.abs(v2c_if[k][s])))
      rs.append(r_)

    r_Is = [-1] * self.cue_num
    for k in range(self.vue_num):
      i = ss[k]
      v2i_if = self.get_V2I(vehicles[k])
      c2i_if = self.get_C2I(i)
      interfere_v2i[k] = v2i_if
      interfere_c2i[k] = c2i_if
      r_Is[i] = self.pc * np.power(np.abs(c2i_if, 2)) / (self.N0 + ps[k] * np.power(np.abs(v2i_if, 2)))
    for i in range(self.cue_num):
      if r_Is[i] == -1:
        c2i_if = self.get_C2I(i)
        interfere_c2i[k] = c2i_if
        r_Is[i] = self.pc * np.power(np.abs(c2i_if, 2)) / self.N0

    betas = [1] * self.vue_num
    r_ks = [0] * self.vue_num
    for k in range(self.vue_num):
      l = ls[k]
      if rs[k] >= self.model_ls[l]['r']:
        betas[k] = self.model_ls[l]['a'] * np.power(np.e, - self.model_ls[l]['b'] * rs[k])
      r_ks[k] = self.model_ls[l]['r_idea'] * (1 - betas[k])

    for k in range(self.vue_num):
      vehicles[k].run(self.slot_cur, r_ks[k])
    end = False
    reward = np.sum(r_Is) + self.lambda_d * (self.vue_num - np.sum(betas))
    for vehicle in vehicles:
      if len(vehicle.data) > 0 and self.slot_cur - vehicle.data[0][0] > self.SLOT_LIMIT:
        end = True
        reward = -1

    package_cur_re_af_trans = np.zeros(self.vue_num)  # package_cur_remain_after_trans
    data_size_remains = np.zeros(self.vue_num)
    for k in range(self.vue_num):
      package_cur_re_af_trans[k] = vehicles[k].data[0][1]
      data_size_remains[k] = np.sum(vehicles[k].data, axis=0)

    state = np.r_[interfere_v2v, interfere_v2i, interfere_v2c, interfere_c2i, package_cur_re_af_trans, data_size_remains]
    return state, reward, end

  def get_V2V(self, vehicle):
    return self.V2Vchannels.get_path_loss((vehicle.distance_v2v[1] - vehicle.distance_v2v[0]) * np.random.random_sample() + vehicle.distance_v2v[0])

  def get_V2I(self, vehicle):
    return self.V2Ichannels.get_path_loss(vehicle.position)

  def get_V2C(self, k, cue_num):
    return [[0] * cue_num]

  def get_C2I(self, i):
    return 0

class V2V_Vehicle:
  # Vehicle simulator: include all the information for a vehicle
  
  def __init__(self, start_position, velocity, distance_v2v, arrival_rate):
    self.slot_cur = 0
    self.position = start_position
    self.velocity = velocity
    self.distance_v2v = distance_v2v
    self.arrival_rate = arrival_rate
    self.data = []
    self.data.append([self.slot_cur,np.random.poisson(self.arrival_rate)])

  def run(self, slot_cur, data_trans):
    self.position += self.velocity
    data_arrive = np.random.poisson(self.arrival_rate)
    self.data.append([slot_cur,data_arrive])
    package_size = 0
    for i in range(len(self.data)):
      package_size += self.data[i][1]
      if package_size > data_trans:
        self.data[i][1] = package_size - data_trans
        break
    self.data = self.data[i:]

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
    self.BS_position = [750 / 2, 1299 / 2]  # center of the grids
    self.shadow_std = 8

  def get_path_loss(self, position):
    d1 = self.BS_position[0]
    d2 = abs(position - self.BS_position[1])
    distance = math.hypot(d1, d2)
    return 128.1 + 37.6 * np.log10(math.sqrt(distance ** 2 + (self.h_bs - self.h_ms) ** 2) / 1000) # + self.shadow_std * np.random.normal()
