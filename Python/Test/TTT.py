import os
import numpy as np

if __name__ == "__main__":
    import xlrd
    data = xlrd.open_workbook('Python2/F4.xlsx')

    data.sheet_names()
    print("sheets:" + str(data.sheet_names()))
    table = data.sheet_by_name(data.sheet_names()[0])
    nrows=table.nrows
    print("总行数：" + str(table.nrows))
    print("总列数：" + str(table.ncols))

    Xs=[[0,1,0,0,0,0],[0,0,1,0,0,0],[0,0,0,1,0,0],[0,0,0,0,1,0],[0,1,1,0,0,0],
    [0,1,0,1,0,0],[0,1,0,0,1,0],[0,0,1,1,0,0],[0,0,1,0,1,0],[0,0,0,1,1,0]]

    np.savetxt('Lingo/M0.TXT',np.loadtxt('Lingo/M00.TXT')*850)
    XYZ=np.loadtxt('XYZ.txt')
    OV=np.loadtxt('OV.txt')

    SX=[]
    SM=[]
    SO=[]
    PO=[]
    M0s=[]

    Mmax=np.loadtxt('Lingo/Mmax0.txt')

    for k in range(len(OV)):

        mmax=(Mmax[0]-Mmax[1])*0.8/60+OV[k]
        np.savetxt('Lingo/Mmax.txt',[mmax])
        np.savetxt('Lingo/Vmin.txt',[OV[k]])
        np.savetxt('Lingo/ZZ.txt',[0])
        np.savetxt('Lingo/XX.txt',[0])
        np.savetxt('Lingo/YY.txt',[0])

        Ms=[]
        Os=[]
        M0=np.loadtxt('Lingo/M0.TXT')
        _Xs=[]
        for i in range(len(Xs)):
            X=[d for d in Xs[i]]
            _Xs.append(X)

            flag=0
            for p in range(len(X)):
                if X[p]==1:
                    if M0[p]<=60:
                        O=10000
                        Ms.append([0,0,0,0,0,0])
                        Os.append(O)
                        flag=1
                        break
            if flag==1:
                continue

            if M0[0]>M0[1] and M0[0]>60:
                X[0]=1
            else:
                X[0]=0
                
            if M0[5]>M0[4] and M0[5]>60:
                X[5]=1
            else:
                X[5]=0
            f = open('Lingo/X.txt','w')
            np.savetxt(f,X)
            f.close()



            os.system("runlingo E:/Cat/Documents/Code/Lingo/P1.ltf")

            M=np.loadtxt('Lingo/m1.TXT')
            O=np.loadtxt('Lingo/o1.TXT')

            Ms.append(M)
            Os.append(O)

        minI=Os.index(min(Os))
        x0=_Xs[minI]
        m0=Ms[minI]
        po=0
        for p in range(1,5):
            if x0[p]==1:
                po+=m0[p]*60
                M0[p]-=m0[p]*60

                if M0[p]<0:
                    M0[p]=1
        if x0[0]==1:
            M0[1]+=m0[0]*60
            M0[0]-=m0[0]*60
            if M0[0]<0:
                M0[0]=1
        if x0[5]==1:
            M0[4]+=m0[5]*60
            M0[5]-=m0[5]*60
            if M0[5]<0:
                M0[5]=1
        PO.append(po)
        M0s.append(M0)
        Mmax[0]-=po
        Mmax[1]-=OV[k]
        np.savetxt('Lingo/M0.TXT',M0)
        SX.append(x0)
        SM.append(Ms[minI])
        SO.append(Os[minI])

    print('SX',SX)
    print('SM',SM)
    print('SO',SO)
    np.savetxt('SX.txt',SX)
    np.savetxt('SO.txt',SO)
    np.savetxt('PO.txt',PO)
    np.savetxt('SM.txt',SM)
    np.savetxt('M0s.txt',M0s)

