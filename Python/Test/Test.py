import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from matplotlib.ticker import MultipleLocator
from mpl_toolkits.mplot3d import Axes3D
import matplotlib as mpl

# coding=utf-8

import xlrd

# 打开文件
data = xlrd.open_workbook('./Python/ttt.xlsx')

# 查看工作表
data.sheet_names()
print("sheets:" + str(data.sheet_names()))

# 通过文件名获得工作表,获取工作表1
table = data.sheet_by_name(data.sheet_names()[1])

# 打印data.sheet_names()可发现，返回的值为一个列表，通过对列表索引操作获得工作表1
# table = data.sheet_by_index(0)

# 获取行数和列数
# 行数：table.nrows
# 列数：table.ncols
print("总行数：" + str(table.nrows))
print("总列数：" + str(table.ncols))

# 获取整行的值 和整列的值，返回的结果为数组
# 整行值：table.row_values(start,end)
# 整列值：table.col_values(start,end)
# 参数 start 为从第几个开始打印，
# end为打印到那个位置结束，默认为none
# print(table.row_values(0))
x=table.col_values(1)[1:-1]
y=table.col_values(1)[1:-1]
z=table.col_values(3)[1:-1]
# print("整列值：" + str(table.col_values(1)))

# print(x[1:-1])

# y=np.sqrt(np.square(x)+np.square(y)+np.square(z))

# 设置图例字号
mpl.rcParams['legend.fontsize'] = 10
fig = plt.figure()

# 设置三维图形模式
ax = fig.gca(projection='3d')

# 测试数据

ax.plot(x, y,z, label='parametric curve')

# 显示图形
plt.show()



0.61669004,0.21669004,-0.28330996,-0.18330996,0.41669004,0.21669004
