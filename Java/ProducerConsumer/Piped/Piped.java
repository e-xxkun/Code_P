import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PipedInputStream;

class Producer implements Runnable {

    private PipedOutputStream pipedOutputStream;

    public Producer() {
        pipedOutputStream = new PipedOutputStream();
    }

    public PipedOutputStream getPipedOutputStream() {
        return pipedOutputStream;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                pipedOutputStream.write(("This is a test, Id=" + i + "!\n").getBytes());
            }
            pipedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private PipedInputStream pipedInputStream;

    public Consumer() {
        pipedInputStream = new PipedInputStream();
    }

    public PipedInputStream getPipedInputStream() {
        return pipedInputStream;
    }

    @Override
    public void run() {
        int len = -1;
        byte[] buffer = new byte[1024];
        try {
            while ((len = pipedInputStream.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, len));
            }
            pipedInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Piped {

    public static void main(String[] args) {
        Producer p = new Producer();
        Consumer c = new Consumer();
        Thread t1 = new Thread(p);
        Thread t2 = new Thread(c);
        try {
            p.getPipedOutputStream().connect(c.getPipedInputStream());
            t2.start();
            t1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}