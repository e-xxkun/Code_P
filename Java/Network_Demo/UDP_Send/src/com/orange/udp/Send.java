package com.orange.udp;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Send {


    private DatagramPacket outPacket;
    private DatagramPacket inPacket;

    private static final int DATA_LEN = 4096;

    public void start() throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket();
        Scanner scanner = new Scanner(System.in);

        byte[] inBuff = new byte[DATA_LEN];
        inPacket = new DatagramPacket(inBuff, inBuff.length);
        SocketAddress receiveAddress = new InetSocketAddress("127.0.0.1", 8888);

        boolean flag = true;
        while (flag) {
            System.out.println("本地：");
            String data = scanner.next();

            byte[] outBuff = data.getBytes();
            if (outPacket == null) {
                outPacket = new DatagramPacket(outBuff, outBuff.length, receiveAddress);
            } else {
                outPacket.setData(outBuff);
            }
            datagramSocket.send(outPacket);

            if ("exit".equals(data)) {
                break;
            }

            datagramSocket.receive(inPacket);
            byte[] receiveData = inPacket.getData();
            String receive = new String(receiveData);

            System.out.println("接收端_"+inPacket.getAddress()+":"+inPacket.getPort()+" ：" + receive);
        }
        //关闭资源
        datagramSocket.close();

    }
    public static void main(String[] args) throws IOException {
        new Send().start();
    }

}
