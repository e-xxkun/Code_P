package com.orange.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.Scanner;

public class Receive {

    private DatagramPacket outPacket;
    private DatagramPacket inPacket;

    private static final int DATA_LEN = 4096;


    public void start() throws IOException {
        //创建服务器
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input port：");
        int port = scanner.nextInt();
        DatagramSocket datagramSocket = new DatagramSocket(port);
        System.out.println("Server start！");

        byte[] bytes = new byte[DATA_LEN];
        inPacket = new DatagramPacket(bytes, bytes.length);

        String address1;
        String address2;

        SocketAddress socketAddress1;
        SocketAddress socketAddress2;

        datagramSocket.receive(inPacket);
        socketAddress1 = inPacket.getSocketAddress();
        address1 = inPacket.getAddress().getHostAddress() + ":" + inPacket.getPort() + ":END";
        System.out.println("IN 1");
        byte[] data = inPacket.getData();
        int length = inPacket.getLength();
        String s = new String(data, 0, length);
        System.out.println("发送端_"+inPacket.getAddress()+":"+inPacket.getPort()+" ：" + s);

        datagramSocket.receive(inPacket);
        socketAddress2 = inPacket.getSocketAddress();
        address2 = inPacket.getAddress().getHostAddress() + ":" + inPacket.getPort() + ":END";
        System.out.println("IN 2");
        data = inPacket.getData();
        length = inPacket.getLength();
        s = new String(data, 0, length);
        System.out.println("发送端_"+inPacket.getAddress()+":"+inPacket.getPort()+" ：" + s);
        boolean flag = true;
        while (flag) {

            System.out.println("本地：");
            String com = scanner.next();

            String out = com + ":" + address2;
            byte[] outBuff = out.getBytes();
            outPacket = new DatagramPacket(outBuff, outBuff.length, socketAddress1);
            datagramSocket.send(outPacket);

            out = com + ":" + address1;
            outBuff = out.getBytes();
            outPacket = new DatagramPacket(outBuff, outBuff.length, socketAddress2);
            datagramSocket.send(outPacket);

        }
        //关闭资源
        datagramSocket.close();

    }

    public static void main(String[] args) throws IOException {
        new Receive().start();
    }
}
