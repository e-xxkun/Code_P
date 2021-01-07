import numpy as np
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
print(data)
print(np.r_[data,data,[5,5,5]])
print(np.c_[data,data,data])
print(np.sum(np.c_[data,data,data], axis=0))