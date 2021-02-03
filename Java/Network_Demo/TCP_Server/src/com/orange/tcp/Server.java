package com.orange.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //定义字符串变量
        String data = null;
        //声明变量
        ServerSocket serverSocket = null;
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;

        try {
            //这里的端口号可以自定义一个整型数，但必须和客户端设置的一致。
            serverSocket = new ServerSocket(8888);
            System.out.println("服务端建立连接成功！！");
            //监听socket是否有数据，这个方法是阻塞式的
            Socket accept = serverSocket.accept();
            //这是连接写入对象
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
            //这是读取连接数据对象
            bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            //定义标识符
            boolean flag = true;
            while (flag) {
                //读取连接的数据
                String Cdata = bufferedReader.readLine();
                if ("exit".equals(Cdata)) {
                    System.out.println("客户端_IP_"+accept.getRemoteSocketAddress()+"退出");
                    break;
                } else {
                    System.out.println("客户端_IP_" + accept.getRemoteSocketAddress() + "：" + Cdata);
                }
                //向客户端发送数据
                System.out.println("服务端：");
                data = scanner.next();
                if ("exit".equals(data)) {
                    flag = false;
                    break;
                }
                //写入数据
                bufferedWriter.write(data);
                //换行
                bufferedWriter.newLine();
                //刷新buffered的缓冲池
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭资源
                serverSocket.close();
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}