public class ThreeABC extends Thread {

  String name;
  String str;
  Object pre;
  Object self;

  public ThreeABC(String name, String str, Object pre, Object self) {
    this.name = name;
    this.str = str;
    this.pre = pre;
    this.self = self;
  }

  public void run() {
    int count = 10;
    while (count > 0) {
      synchronized(pre) {
        synchronized(self) {
          System.out.println(name + ":" + str);
          count --;
          self.notify();
        }
        try {
          if (count == 0) {
            pre.notify();
          } else {
            pre.wait();
          }
        } catch (Exception e) {
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    Object a = new Object();
    Object b = new Object();
    Object c = new Object();

    ThreeABC t1 = new ThreeABC("T1", "A", c, a);
    ThreeABC t2 = new ThreeABC("T2", "B", a, b);
    ThreeABC t3 = new ThreeABC("T3", "C", b, c);

    t1.start();
    Thread.sleep(2);
    t2.start();
    Thread.sleep(2);
    t3.start();
  }
}