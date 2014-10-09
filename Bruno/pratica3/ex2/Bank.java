import java.lang.Thread;

class Account {
  private int id;
  public int balance;

  public Account() {
    balance = 0;
  }
}

class Bank {
  Account[] accounts;

  public Bank(int N) {
    accounts = new Account[N];
    for(int i = 0; i < N; i++) {
      accounts[i] = new Account();
    }
  }

  synchronized public void debit(int id, int amount) {
    accounts[id].balance -= amount;
  }

  synchronized public void credit(int id, int amount) {
    accounts[id].balance += amount;
  }

  synchronized public int getBalance(int id) {
    return accounts[id].balance;
  }

  synchronized public void transfer(int acc1, int acc2, int amount) {
    this.debit(acc1,amount);
    this.credit(acc2,amount);
  }

}

class Main {
  public static void main(String[] args) throws InterruptedException {
    int N = 100;
    final Bank bank = new Bank(N);

    for(int i = 0; i < N; i++) {
      bank.credit(i,1000);
    }

    class DebitT extends Thread {
      public void run() {
        int acc = (int) (Math.random() * 100);
        bank.debit(acc,500);
      }
    }

    class CreditT extends Thread {
      public void run() {
        int acc = (int) (Math.random() * 100);
        bank.credit(acc,500);
      }
    }

    class TransferT extends Thread {
      private int acc1, acc2;
      private int amount;

      public TransferT(int acc1, int acc2, int amount) {
        this.acc1 = acc1;
        this.acc2 = acc2;
        this.amount = amount;
      }

      public void run() {
        bank.transfer(acc1,acc2,amount);
      }
    }
    /*
    DebitT[] dt = new DebitT[N];
    CreditT[] ct = new CreditT[N];

    for(int i = 0; i < N; i++) {
      dt[i] = new DebitT(); dt[i].start();
      ct[i] = new CreditT(); ct[i].start();
    }

    for(int i = 0; i < N; i++) {
      dt[i].join();
      ct[i].join();
    }
    */
    TransferT[] tt = new TransferT[N];
    for(int i = 0; i < N/2; i++) {
      tt[i] = new TransferT(i,(N/2) + i,500);
      tt[i].start();
    }

    for(int i = 0; i < N/2; i++) {
      tt[i].join();
    }

    for(int i = 0; i < N; i++) {
      System.out.println("Account: " + i + " Balance: " + bank.getBalance(i));
    }
  }
}
