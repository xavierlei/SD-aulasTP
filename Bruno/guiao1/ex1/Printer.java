import java.lang.Thread;

class Printer extends Thread {
  final int i;

  public Printer(int i) {
    this.i = i;
  }

  public void run(){
    for(int j = 0; j < i; j++){
      System.out.println(j);
    }
  }
}

class Main {
  private static int N = 100;
  private static int I = 100;

  public static void main(String[] args) throws InterruptedException {
    Printer[] threads = new Printer[N];

    for(int i = 0; i < N; i++) {
      threads[i] = new Printer(I);
    }

    for(int i = 0; i < N; i++) {
      threads[i].start();
    }
  }
}
