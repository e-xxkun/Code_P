package com.orange.tcp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        //创建键盘录入对象
        Scanner scanner = new Scanner(System.in);
        String data = null;
        //声明变量
        Socket socket = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            //设置通信连接配置
            //这里的端口号可以自定义一个整型数，但必须和服务端设置的一致。
            socket = new Socket("localhost", 8888);
            //读取socket连接的数据
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //向连接发送数据
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //定义标识位
            boolean flag = true;
            while (flag) {
                System.out.println("客户端：");
                data = scanner.next();
                //写入数据
                bufferedWriter.write(data);
                //增加换行
                bufferedWriter.newLine();
                //刷新buffered缓冲池
                bufferedWriter.flush();
                if ("exit".equals(data)){
                    flag = false;
                    break;
                }
                //读取服务端的数据
                String SData = bufferedReader.readLine();


                //打印连接的数据
                System.out.println("服务端_IP_"+socket.getRemoteSocketAddress()+"：" + SData);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                socket.close();
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}