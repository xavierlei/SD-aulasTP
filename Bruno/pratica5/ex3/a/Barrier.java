class Barrier {
  int N;
  int nw; // number of threads waiting

  public Barrier(int N) { this.N = N; }

  synchronized public void w() throws InterruptedException {  // wait
    nw++;
    // while(nw < N)
    //   wait();
    // notifyAll();
    if(nw < N) {
      while(nw < N)
        wait();
    } else notifyAll();
  }
}

class Main {
  public static void main(String args[]) throws InterruptedException {
    final int N = 3;
    final Barrier b = new Barrier(N);

    class Printer extends Thread {
      int id;

      public Printer(int id) { this.id = id; }

      public void run() {
        for(int i = 0; i < 10; i++)
          System.out.println(id + ": Running towards barrier!");
        try { b.w(); } catch(InterruptedException e) {}
        System.out.println(id + ": Running away from barrier!");
      }
    }

    Printer p[] = new Printer[N];
    for(int i = 0;i < N; i++) {
      p[i] = new Printer(i);
      p[i].start();
    }

    for(int i = 0; i < N; i++)
      p[i].join();
  }
}
