import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    //输入scanner
    private Scanner scanner = new Scanner(System.in);
    //是否等待输入
    private boolean isWaitInput = true;
    //首次与外网主机通信的连接
    private Socket socket;
    //首次与外网主机通信的本地端口
    private int localPort;

    private PrintWriter pw;
    private BufferedReader br;

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        try {
            // 新建一个socket通道
            socket = new Socket();
            // 设置reuseAddress为true
            socket.setReuseAddress(true);

            //TODO在此输入外网地址和端口
            String ip = "xxx.xxxx.xxxx.xxxx";
            int port = 8888;
            socket.connect(new InetSocketAddress(ip, port));

            localPort = socket.getLocalPort();

            System.out.println("本地端口：" + localPort);
            System.out.println("请输入命令 notwait等待穿透，或者输入conn进行穿透");

            pw = new PrintWriter(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processLocalCommand(String in) throws IOException {
        if ("conn".equals(in)) {
            System.out.println("请输入要连接的目标外网ip:");
            String ip = scanner.next();
            System.out.println("请输入要连接的目标外网端口:");
            int port = scanner.nextInt();

            pw.write("newConn_" + ip + "_" + port + "\n");
            pw.flush();

            doPenetration(ip, port);

            isWaitInput = false;
        }
    }

    /*
     * 对目标服务器进行穿透
     */
    private void doPenetration(String ip, int port) {
        try {
            //异步对目标发起连接
            new Thread() {
                public void run() {
                    try {

                        Socket newsocket = new Socket();

                        newsocket.setReuseAddress(true);
                        newsocket.bind(new InetSocketAddress(
                                InetAddress.getLocalHost().getHostAddress(), localPort));

                        System.out.println("connect to " + new InetSocketAddress(ip, port));

                        newsocket.connect(new InetSocketAddress(ip, port));

                        System.out.println("connect success");

                        BufferedReader b = new BufferedReader(
                                new InputStreamReader(newsocket.getInputStream()));
                        PrintWriter p = new PrintWriter(newsocket.getOutputStream());

                        while (true) {

                            p.write("hello " + System.currentTimeMillis() + "\n");
                            p.flush();

                            String message = b.readLine();
                            System.out.println(message);

                            pw.write(message + "\n");
                            pw.flush();

                            if("exit".equals(message)) {
                                break;
                            }

                            Thread.sleep(1000l);
                        }

                        b.close();
                        p.close();
                        newsocket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();

//			//监听本地端口
//			ServerSocket serverSocket = new ServerSocket();
//			serverSocket.setReuseAddress(true);
//			serverSocket.bind(new InetSocketAddress(InetAddress.getLoopbackAddress(), localPort));
//
//			// 记录客户端的数量
//			System.out.println("******开始监听端口：" + localPort);
//			// 循环监听等待客户端的连接
//			// 调用accept()方法开始监听，等待客户端的连接
//			Socket st = serverSocket.accept();
//
//			System.out.println("成功了，哈哈，新的连接：" + st.getInetAddress().getHostAddress() + ":" + st.getPort());
//
//			serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("监听端口 " + socket.getLocalPort() + " 出错");
        }
    }
}
