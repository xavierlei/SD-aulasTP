class Barrier {
  int N;
  int nw; // number of threads waiting
  int stage;

  public Barrier(int N) { this.N = N; }

  synchronized public void w() throws InterruptedException {  // wait
    nw++;
    int entry_stage = stage;
    if(nw == N) {
      stage++;
      nw = 0;
      notifyAll();
    } else {
      while(entry_stage == stage) {
        wait();
      }
    }
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
        System.out.println(id + ": Stage 1!");
        try { b.w(); } catch(InterruptedException e) {}
        System.out.println(id + ": Stage 2!");
        try { b.w(); } catch(InterruptedException e) {}
        System.out.println(id + ": Stage 3!");
        try { b.w(); } catch(InterruptedException e) {}
        System.out.println(id + ": Stage 4!");
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
