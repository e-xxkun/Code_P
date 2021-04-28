import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<ServerThread> connections = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket;
            int count = 0;
            System.out.println("***服务器即将启动，等待客户端的连接***");
            while (true) {
                socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
                connections.add(serverThread);
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
                pw.write("已有客户端列表：" + connections + "\n");

                String info;
                while ((info = br.readLine()) != null) {
                    System.out.println("我是服务器，客户端说：" + info);

                    if (info.startsWith("newConn_")) {
                        String[] infos = info.split("_");
                        String ip = infos[1];
                        String port = infos[2];
                        System.out.println("打洞到 " + ip + ":" + port);
                        for (ServerThread server : connections) {
                            if (server.socket.getInetAddress().getHostAddress().equals(ip)
                                    && server.socket.getPort() == Integer.parseInt(port)) {

                                server.pw.write("autoConn_" + socket.getInetAddress().getHostAddress() + "_" + socket.getPort()
                                        + "\n");
                                server.pw.flush();

                                break;
                            }
                        }
                    } else {
                        pw.write("欢迎您！" + info + "\n");
                        pw.flush();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("客户端关闭：" + address.getHostAddress() + " ,端口：" + socket.getPort());
                connections.remove(this);
                try {
                    if (pw != null) {
                        pw.close();
                    }
                    if (br != null) {
                        br.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public String toString() {
            return "ServerThread [socket=" + socket + "]";
        }
    }
}
