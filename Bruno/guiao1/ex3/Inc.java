import java.lang.Thread;

class Counter {
  public int counter;

  public Counter() {
    this.counter = 0;
  }

  public void increment() {
    counter++;
  }
}

class Main {
  public static void main(String[] args) throws InterruptedException {
    final int N = 10;
    final int I = 10000;
    final Counter c = new Counter();

    class Incrementer extends Thread{
      public void run() {
        for(int i = 0; i < I; i++){
          //c.increment();
          c.counter++;
        }
      }
    }

    Incrementer[] threads = new Incrementer[N];

    for(int i = 0; i < N; i++) {
      threads[i] = new Incrementer();
      threads[i].start();
    }

    for(int i = 0; i < N; i++) {
      threads[i].join();
    }

    System.out.println("Counter -> " + c.counter);
  }
}
