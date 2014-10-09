import java.lang.Thread;

class Counter {
  private int counter;  // com encapsulamento
  // public int counter;  // sem encapsulamento

  public Counter() {
    this.counter = 0;
  }

  synchronized public void increment() {
    counter++;
  }

  synchronized public int getCounter() {
    return counter;
  }
}

class Main {
  public static void main(String[] args) throws InterruptedException {
    final int N = 10;
    final int I = 1000000;
    final Counter c = new Counter();

    class Incrementer extends Thread{
      public void run() {
        for(int i = 0; i < I; i++){
          c.increment();  // com encapsulamento
          /*
          synchronized (c) {  // sem encapsulamento
            c.counter++;
          }
          */
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

    // System.out.println("Counter -> " + c.counter); // sem encapsulamento
    System.out.println("Counter -> " + c.getCounter()); // com encapsulamento
  }
}
