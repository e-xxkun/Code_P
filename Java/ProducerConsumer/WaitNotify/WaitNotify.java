import java.util.LinkedList;

class Storage{

    private final int MAX_SIZE = 10;

    private LinkedList<Object> list = new LinkedList<>();

    public void produce(){
        synchronized(list){
            while(list.size()>=MAX_SIZE){
                System.out.println("|Producer"+Thread.currentThread().getName()+"| 仓库已满");
                try{
                    list.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            list.add(new Object());
            System.out.println("|Producer"+Thread.currentThread().getName()+"| 生产1个产品，现有库存"+list.size());
            list.notifyAll();
        }
    }

    public void consume(){
        synchronized(list){
            while(list.size()==0){
                System.out.println("|Consumer"+Thread.currentThread().getName()+"| 仓库为空");
                try{
                    list.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            list.remove();
            System.out.println("|Consumer"+Thread.currentThread().getName()+"| 消费1个产品，现有库存"+list.size());
            list.notifyAll();
        }
    }
}

class Producer implements Runnable{

    private Storage storage;

    public Producer(Storage storage){
        this.storage = storage;
    }

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(1000);
                storage.produce();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{

    private Storage storage;

    public Consumer(Storage storage){
        this.storage = storage;
    }

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(3000);
                storage.consume();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

public class WaitNotify{

    public static void main(String[] args) {
        Storage storage = new Storage();

        Thread p1 = new Thread(new Producer(storage));
        Thread p2 = new Thread(new Producer(storage));
        Thread p3 = new Thread(new Producer(storage));

        Thread c1 = new Thread(new Consumer(storage));
        Thread c2 = new Thread(new Consumer(storage));
        Thread c3 = new Thread(new Consumer(storage));

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
    }
}