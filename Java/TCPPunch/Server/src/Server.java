import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    static ServerSocket serverSocket;
    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(8888);
            Socket socket;
            System.out.println("***服务器即将启动，等待客户端的连接***");
            new TThread().start();
            while (true) {
                socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class TThread extends Thread {
        @Override
        public void run() {

            Scanner in = new Scanner(System.in);
            in.nextLine();
            try {
                Socket socket = new Socket();

                socket.setReuseAddress(true);
                socket.bind(new InetSocketAddress(
                        "127.0.0.1", 8888));
                String ip = "127.0.0.1";
                int port = 8800;
                socket.connect(new InetSocketAddress(ip, port));
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                pw.write(in.nextLine() + "\n");
                pw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class SSocket extends Socket {
        @Override
        public void connect(SocketAddress endpoint) throws IOException {

            super.connect(endpoint);
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
                pw.write(in.nextLine() + "\n");
                pw.flush();
                while ((info = br.readLine()) != null) {
                    System.out.println("服务器:" + socket.getRemoteSocketAddress() + " -> " + info);

                    pw.write(in.nextLine() + "\n");
                    pw.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}