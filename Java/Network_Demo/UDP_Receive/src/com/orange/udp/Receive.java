package com.orange.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class Receive {

    private DatagramPacket outPacket;
    private DatagramPacket inPacket;

    private static final int DATA_LEN = 4096;


    public void start() throws IOException {
        //创建服务器
        DatagramSocket datagramSocket = new DatagramSocket(8888);
        System.out.println("服务器开启成功！");

        byte[] bytes = new byte[DATA_LEN];
        inPacket = new DatagramPacket(bytes, bytes.length);
        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            datagramSocket.receive(inPacket);

            byte[] data = inPacket.getData();
            int length = inPacket.getLength();
            String s = new String(data, 0, length);

            if (s.equals("exit")) {
                System.out.println("发送端_"+inPacket.getAddress()+":"+inPacket.getPort()+" ：关闭连接");
                break;
            }
            System.out.println("发送端_"+inPacket.getAddress()+":"+inPacket.getPort()+" ：" + s);

            System.out.println("本地：");
            String res = scanner.next();
            byte[] send = res.getBytes();
            outPacket = new DatagramPacket(send, send.length, inPacket.getSocketAddress());
            if ("exit".equals(res)){
                flag = false;
            }

            datagramSocket.send(outPacket);

        }
        //关闭资源
        datagramSocket.close();

    }

    public static void main(String[] args) throws IOException {
        new Receive().start();
    }
}
