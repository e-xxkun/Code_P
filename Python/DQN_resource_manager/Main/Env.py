import numpy as np
import pickle
import math

class Env():

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

  # def generate(self, slot_num=1000, cue_num=50, vue_num=50, velocity=[30,40], distance_v2v=[70,80], url='./env_2021_1_6'):
  #   self.V2Vchannels = V2Vchannels()
  #   self.V2Ichannels = V2Ichannels()
  #   envs = []

  def update_state(self, vehicles, action): #action: p, s, l
    ps = action['p']
    ss = action['s']
    ls = action['l']

    rs = []
    for k in range(self.vue_num):
      vehicle = vehicles[k]
      v2v_if = self.get_V2V(vehicle)
      v2c_if = self.get_V2C(k, self.cue_num)
      s = ss[k]
      r_ = ps[k] * np.power(np.abs(v2v_if),2) / (self.N0 + self.pc * np.power(np.abs(v2c_if[k][s])))
      rs.append(r_)

    r_Is = [0] * self.cue_num
    for k in range(self.vue_num):
      i = ss[k]
      r_Is[i] = self.pc * np.power(np.abs(self.get_C2I(i)), 2) / (self.N0 + ps[k] * np.power(np.abs(self.get_V2I(vehicles[k]), 2)))
    for i in range(self.cue_num):
      if r_Is[i] == 0:
        r_Is[i] = self.pc * np.power(np.abs(self.get_C2I(i)), 2) / self.N0

    betas = [1] * self.vue_num
    r_ks = [0] * self.vue_num
    for k in range(self.vue_num):
      l = ls[k]
      if rs[k] >= self.model_ls[l]['r']:
        betas[k] = self.model_ls[l]['a'] * np.power(np.e, - self.model_ls[l]['b'] * rs[k])
      r_ks[k] = self.model_ls[l]['r_idea'] * (1 - betas[k])

    for k in range(self.vue_num):
      vehicles[k].run(r_ks[k])

    return state, reward

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
    self.position = start_position
    self.velocity = velocity
    self.distance_v2v = distance_v2v
    self.arrival_rate = arrival_rate
    self.data = np.random.poisson(self.arrival_rate)

  def run(self, data_trans):
    self.position += self.velocity
    data_arrive = np.random.poisson(self.arrival_rate)
    self.data += data_arrive - data_trans

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
