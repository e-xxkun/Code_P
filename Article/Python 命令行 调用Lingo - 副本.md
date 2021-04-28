## 前言

近期参加了2020界研究生数学建模大赛，选的F题用最优化方法做的需要求解一个复杂的带约束规划问题，所以学习了Lingo的使用

## Lingo基本命令

### 求解基本的线性问题

$$
\begin{aligned}
min\quad& 2x_1+3x_2\\
s.t.\quad& x_1+x_2 \geq 350\\
& x_1 \qquad \geq 100\\
& 2x_1+x_2\leq 600\\
& x_1,x_2 \geq 0
\end{aligned}
$$

Lingo代码

```
min = 2 * x1 + 3 * x2;
x1 + x2 >= 350;
x1 >= 100;
2 * x1 + x2 <= 600;
```

### 问题模型

例，指派问题  
拟分配$n$人去干$n$项工作,每人干且仅干一项工作,若分配第$i$人去干第$j$项工作,需花费$c_{ij}$单位时间,问应如何分配工作才能使工人花费的总时间最少?  
容易看出,要给出一个指派问题的实例,只需给出矩阵$C=(c_{i,j})$, $C$被称为指派问题的系数矩阵。  
引入变量$x_{ij}$,若分配$i$干$j$工作,则取$x_{ij}=1$,否则取$x_{ij}=0$。上述指派问题的数学模型为

$$
\begin{aligned}
min\quad& \sum_{i=1}^n\sum_{j=1}^n c_{ij} x_{ij}\\
&\sum_{j=1}^n x_{ij} = 1\\
&\sum_{i=1}^n x_{ij} = 1\\
&x_{ij}={0,1}
\end{aligned}
$$

Lingo代码

```
Model: 

Sets: 

var/1..5/; 
link(var,var):c,x; 

Endsets 

Min=@sum(link:c*x); 

@for(var(i):@sum(var(j):x(i,j))=1); 
@for(var(j):@sum(var(i):x(i,j))=1); 
@for(link:@bin(x)); 

Data: 

c=3 8 2 10 3   
  8 7 2 9 7   
  6 4 2 7 5   
  8 4 2 3 5   
  9 10 6 9 10; 

Enddata 

End 
```