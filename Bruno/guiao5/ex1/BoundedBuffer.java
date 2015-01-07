import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*

*/
class BoundedBuffer {
  final int N;
  int n, ip, ig; // number of elements in the array, index_put, index_get
  int arr[];
  Lock l;
  Condition notEmpty, notFull;

  public BoundedBuffer(int N) {
    this.N = N;
    arr = new int[N];
    l = new ReentrantLock();
    notEmpty = l.newCondition();
    notFull = l.newCondition();
  }

  public void put(int v) throws InterruptedException {
    l.lock();
    try {
      while(n == N)
        notFull.await();
      arr[ip] = v;
      ip = (ip + 1) % N;
      n++;
      notEmpty.signal(); //  I put an element therefore the buffer is certainly not empty anymore!
    } finally { l.unlock(); }
  }

  public int get() throws InterruptedException {
    int r;
    l.lock();
    try {
      while(n == 0)
        notEmpty.await();
      r = arr[ig];
      ig = (ig + 1) % N;
      n--;
      notFull.signal();  //  I got an element therefore the buffer is certainly not full anymore!
      return r;
    } finally { l.unlock(); }
  }

  public void consult() throws InterruptedException {
    l.lock();
    try {
      if(n == N)
        System.out.println("Buffer Full");
      if(n == 0)
        System.out.println("Buffer Empty");
    } finally { l.unlock(); }
  }
}

class Main {
  public static void main(String args[]) throws InterruptedException {
    final int N = 10;
    final BoundedBuffer bb = new BoundedBuffer(N);

    class Putter extends Thread {
      public void run() {
        try {
            while(true)
              bb.put((int) (Math.random() * 100));
        } catch(InterruptedException e){}
      }
    }

    class Getter extends Thread {
      int r;
      public void run() {
        try {
          while(true)
            r = bb.get();
        } catch(InterruptedException e) {}
      }
    }

    class Consulter extends Thread {
      public void run() {
        try {
          while(true)
            bb.consult();
        } catch(InterruptedException e) {}
      }
    }

    Putter p = new Putter();
    Getter g = new Getter();
    Consulter c = new Consulter();
    p.start(); g.start(); c.start();
    p.join(); g.join(); c.join();
  }
}
