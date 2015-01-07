/* notifyAll() is used since the same condition variable is tested in different contexts (is the buffer full/empty?)
* notify() could wake the "wrong" thread (a thread that would not be interested in doing anything in a specific context,
* a producer thread instead of a consumer, for example), resulting in a deadlock
* In conclusion, one condition variable and different reasons/contexts => notifyAll()
*/
class BoundedBuffer {
  final int N;
  int n, ip, ig; // number of elements in the array, index_put, index_get
  int arr[];

  public BoundedBuffer(int N) {
    this.N = N;
    arr = new int[N];
  }

  synchronized public void put(int v) throws InterruptedException {
    while(n == N) // wait while array full
      wait();
    arr[ip] = v;
    ip = (ip + 1) % N;
    n++;
    notifyAll();
  }

  synchronized public int get() throws InterruptedException {
    while(n == 0) // wait while array empty
      wait();
    int r = arr[ig];
    ig = (ig + 1) % N;
    n--;
    notifyAll();
    return r;
  }

  synchronized public void consult() {
    if(n == N)
      System.out.println("Buffer is full!");
    if(n == 0)
      System.out.println("Buffer is empty!");
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
        while(true)
          bb.consult();
      }
    }

    Putter p = new Putter();
    Getter g = new Getter();
    Consulter c = new Consulter();
    p.start(); g.start(); c.start();
    p.join(); g.join(); c.join();
  }
}
