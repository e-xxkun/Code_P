import numpy as np
import pandas as pd

data = [5,3,7,0,0,5]
print(data[0:])
print(sum(data[2:]))
data_trans = 3
package_size = 0
for i in range(len(data)):
  package_size += data[i]
  if package_size > data_trans:
    data[i] = package_size - data_trans
    break
data = data[i:]
# print(data)
# print(np.r_[data,data,[5,5,5]])
# print(np.c_[data,data,data])
# print(np.sum(np.c_[data,data,data], axis=0))
print(np.random.randint(0,1,size=20))
print('--------------')
p = np.random.randint(-6, 25, size=20)
p[p < 0] = 1
print(p)
print(pd.cut(p, [0, 7,12,17,21,25], labels=[5, 10, 15, 20, 23]))
print(pd.cut(p, 5, labels=False))

