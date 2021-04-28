import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1",8888);

            //构建IO
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            Scanner in = new Scanner(System.in);
            while (true) {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                //向服务器端发送一条消息

                bw.write(in.nextLine() + "\n");
                bw.flush();

                //读取服务器返回的消息
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String mess = br.readLine();
                System.out.println("服务器：" + s.getRemoteSocketAddress() + " -> " + mess);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}