import numpy as np
# import pandas as pd

# data = [5,3,7,0,0,5]
# print(data[0:])
# print(sum(data[2:]))
# data_trans = 3
# package_size = 0
# for i in range(len(data)):
#   package_size += data[i]
#   if package_size > data_trans:
#     data[i] = package_size - data_trans
#     break
# data = data[i:]
# print(data)
# print(np.r_[data,data,[5,5,5]])
# print(np.c_[data,data,data])
# print(np.sum(np.c_[data,data,data], axis=0))
# print(np.random.randint(0,1,size=20))
# print('--------------')
# p = np.random.randint(-6, 25, size=20)
# p[p < 0] = 1
# print(p)
# print(pd.cut(p, [0, 7,12,17,21,25], labels=[5, 10, 15, 20, 23]))
# print(pd.cut(p, 5, labels=False))
# print(np.random.poisson(0.01,10))

p = np.empty((4,2,3))
p[1,:,:] = np.array([[1,2,3],[5,6,7]])
# print(p)

n = np.array([[1,2,3,4,5,6]])
n[0,[0,2]] = [99, 88]
# print(n)
f = 18
v_num = 2
amap = np.empty([np.power(f, v_num), v_num * 3], dtype=np.int)
p_state = [6, 12, 18]
map_tmp = -1
for v in range(v_num):
  index = 0
  diff = np.power(f, v)
  for p in range(3):
    for s in range(2):
      for l in range(3):
        amap[index:index + diff, v * 3:v * 3 + 3] = [p_state[p], s, l]
        amap[index:index + diff, :v * 3] = map_tmp
        index += diff
  map_tmp = amap[:index, :v * 3 + 3]
print(amap[:20,:])

actions_map_sort = np.empty([np.power(f, v_num), v_num * 3], dtype=np.int)
index = 0
for i in range(3):
  for j in range(v_num):
    actions_map_sort[:, index] = amap[:, j * 3 + i]
    index += 1
# print(actions_map_sort[:20,:])

# print(3.98 * 10 ** -12)
mcs = [
  {'a':5.521,   'b':1.521,  'r':1.123,  'r_idea':120},
  {'a':16.7,    'b':0.635,  'r':4.428,  'r_idea':280},
  {'a':15.12,   'b':0.121,  'r':22.428, 'r_idea':502}
]
import matplotlib.pyplot as plt
for n in range(3):
  index = mcs[n]['r'] + np.arange(200) * 0.1
  y = mcs[n]['a']* np.exp(- mcs[n]['b'] * index)
  plt.plot(index, y)

plt.show()