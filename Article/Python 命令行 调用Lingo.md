## 前言

近期参加了2020界研究生数学建模大赛，选的F题用最优化方法做的需要求解一个复杂的带约束规划问题，所以学习了Lingo的使用

## Lingo基本命令

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