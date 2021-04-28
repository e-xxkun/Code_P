import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8800);
            Socket socket;
            int count = 0;
            System.out.println("***服务器即将启动，等待客户端的连接***");
            while (true) {
                socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
                count++;
                System.out.println("客户端的数量：" + count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static class ServerThread extends Thread {
        private Socket socket;
        private BufferedReader br;
        private PrintWriter pw;

        public ServerThread(Socket socket) throws IOException {
            this.socket = socket;
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.pw = new PrintWriter(socket.getOutputStream());
        }

        public void run() {

            InetAddress address = socket.getInetAddress();
            System.out.println("新连接，客户端的IP：" + address.getHostAddress() + " ,端口：" + socket.getPort());

            try {
                Scanner in = new Scanner(System.in);
                String info;
                System.out.println(socket.getPort());
                while ((info = br.readLine()) != null) {
                    System.out.println("我是服务器，客户端说：" + info);

                    pw.write(in.nextLine() + "\n");
                    pw.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}