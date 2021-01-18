package com.orange.udp;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Send extends Thread{


    private DatagramPacket outPacket;
    private DatagramPacket inPacket;

    private String HOST = "106.14.249.225";

    private static final int DATA_LEN = 4096;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input local port:");
        int lport = scanner.nextInt();
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(lport);

            String hostname = HOST;

            System.out.println("Input port:");
            int port = scanner.nextInt();
            byte[] inBuff = new byte[DATA_LEN];
            inPacket = new DatagramPacket(inBuff, inBuff.length);
            SocketAddress receiveAddress = new InetSocketAddress(hostname, port);

            boolean flag = true;
            while (flag) {

        //        System.out.println("Input hostname:");
        //        String hostname = scanner.next();
                System.out.println("Local：");
                String data = scanner.next();

                byte[] outBuff = data.getBytes();
                if (outPacket == null) {
                    outPacket = new DatagramPacket(outBuff, outBuff.length, receiveAddress);
                } else {
                    outPacket.setData(outBuff);
                }
                datagramSocket.send(outPacket);

                datagramSocket.receive(inPacket);
                byte[] receiveData = inPacket.getData();
                String receive = new String(receiveData);

                System.out.println("Receive_"+inPacket.getAddress()+":"+inPacket.getPort()+" ：" + receive);
                if (receive.startsWith("S:")) {
                    String[] UDPData = receive.split(":");
                    SocketAddress address = new InetSocketAddress(UDPData[1], Integer.parseInt(UDPData[2]));

                    for (int i = 0;i < 19;i ++) {
                        String send = "CHECK ";
                        System.out.println("SEND " + send + i);
                        outBuff = send.getBytes();
                        outPacket = new DatagramPacket(outBuff, outBuff.length, address);
                        outPacket.setData(outBuff);
                        datagramSocket.send(outPacket);
                        sleep(500);
                    }
                    for (int i = 0;i < 10;i ++) {
                        System.out.println("Wait : " + outPacket.getSocketAddress());
                        datagramSocket.receive(inPacket);
                        inBuff = inPacket.getData();
                        String inData = new String(inBuff);
                        System.out.println("Receive_"+inPacket.getAddress()+":"+inPacket.getPort()+" ：" + inData);
//                    if (inData.equals(send)) {
//                    }
                    }
                    while (flag) {
                        System.out.println("Wait : " + outPacket.getSocketAddress());
                        String send = scanner.next();
                        System.out.println("SEND " + send);
                        outBuff = send.getBytes();
                        outPacket.setData(outBuff);
                        datagramSocket.send(outPacket);
                        System.out.println("Wait : " + outPacket.getSocketAddress());
                        datagramSocket.receive(inPacket);
                        inBuff = inPacket.getData();
                        String inData = new String(inBuff);
                        System.out.println("Receive_"+inPacket.getAddress()+":"+inPacket.getPort()+" ：" + inData);
//
                    }
                }
            }
            //关闭资源
            datagramSocket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void start2() throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input local port:");
        int lport = scanner.nextInt();
        DatagramSocket datagramSocket = new DatagramSocket(lport);

        boolean flag = true;
        while (flag) {
            System.out.println("Input Command:");
            String command = scanner.next();
            switch (command) {
                case "ss":
                    String hostname1 = "106.14.249.225";
                    System.out.println("Input remote port:");
                    int port1 = scanner.nextInt();
                    SocketAddress receiveAddress1 = new InetSocketAddress(hostname1, port1);
                    System.out.println("SEND：");
                    String data1 = scanner.next();
                    byte[] outBuff1 = data1.getBytes();
                    outPacket = new DatagramPacket(outBuff1, outBuff1.length, receiveAddress1);
                    datagramSocket.send(outPacket);
                    System.out.println("Send to: " + hostname1 + ":" + port1);
                    break;
                case "s":
                    System.out.println("Input remote hostname:");
                    String hostname = scanner.next();
                    System.out.println("Input remote port:");
                    int port = scanner.nextInt();
                    SocketAddress receiveAddress = new InetSocketAddress(hostname, port);
                    System.out.println("SEND：");
                    String data = scanner.next();
                    byte[] outBuff = data.getBytes();
                    outPacket = new DatagramPacket(outBuff, outBuff.length, receiveAddress);
                    datagramSocket.send(outPacket);
                    System.out.println("Send to: " + hostname + ":" + port);
                    break;
                case "sa":
                    System.out.println("Input remote hostname:");
                    String hostname2 = scanner.next();
                    System.out.println("Input remote port:");
                    int port2 = scanner.nextInt();
                    System.out.println("Range of remote port:");
                    int num = scanner.nextInt();
                    System.out.println("SEND：");
                    String data2 = scanner.next();
                    for (int i = 0;i < num;i ++) {
                        int _port = port2 + i;
                        SocketAddress receiveAddress2 = new InetSocketAddress(hostname2, _port);
                        byte[] outBuff2 = data2.getBytes();
                        outPacket = new DatagramPacket(outBuff2, outBuff2.length, receiveAddress2);
                        datagramSocket.send(outPacket);
                        System.out.println("Send to: " + hostname2 + ":" + _port);

                        _port = port2 - i;
                        if (_port < 0) {
                            continue;
                        }
                        receiveAddress2 = new InetSocketAddress(hostname2, _port);
                        outBuff2 = data2.getBytes();
                        outPacket = new DatagramPacket(outBuff2, outBuff2.length, receiveAddress2);
                        datagramSocket.send(outPacket);
                        System.out.println("Send to: " + hostname2 + ":" + _port);
                    }
                    break;
                case "r":
                    byte[] inBuff = new byte[DATA_LEN];
                    inPacket = new DatagramPacket(inBuff, inBuff.length);
                    System.out.println("Wait to receive:");
                    datagramSocket.receive(inPacket);
                    inBuff = inPacket.getData();
                    String inData = new String(inBuff);
                    System.out.println("Receive from "+inPacket.getAddress()+":"+inPacket.getPort()+" ：" + inData);
                    break;
            }
        }
        //关闭资源
        datagramSocket.close();

    }

    public static void main(String[] args) throws IOException {
        new Send().start2();
    }

}
