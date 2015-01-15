/*
* exclusao mutua ao nivel das contas -> banco nao fica bloqueado quando ocorrem operacoes entre contas diferentes
* uma thread que trabalhe numa conta bloqueia acesso a outra -> maior concorrencia
*/

import java.lang.Thread;

class Account {
  public int balance;

  synchronized public void withdraw(int amount) {
    balance -= amount;
  }

  synchronized public void deposit(int amount) {
    balance += amount;
  }

  synchronized public int getBalance() {
    return balance;
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

  public void withdraw(int id, int amount) {
    accounts[id].withdraw(amount);
  }

  public void deposit(int id, int amount) {
    accounts[id].deposit(amount);
  }

  public int getBalance(int id) {
    return accounts[id].getBalance();
  }

  public void transfer(int acc1, int acc2, int amount) {
    int i1, i2;
    if(acc1 < acc2) {
      i1 = acc1; i2 = acc2;
    }
    else {
      i1 = acc2; i2 = acc1;
    }

    synchronized(accounts[i1]){
      synchronized(accounts[i2]){
        accounts[acc1].withdraw(amount);
        accounts[acc2].deposit(amount);
      }
    }
  }
}

class Main {
  public static void main(String[] args) throws InterruptedException {
    int N = 1000;
    final Bank bank = new Bank(N);

    for(int i = 0; i < N; i++) {
      bank.deposit(i,10000);
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

    TransferT[] tt = new TransferT[N];

    for(int i = 0; i < N; i++) {
      tt[i] = new TransferT(0,1,5);
      tt[i].start();
    }

    for(int i = 0; i < N; i++) {
      tt[i].join();
    }

    for(int i = 0; i < 2; i++) {
      System.out.println("Account: " + i + " Balance: " + bank.getBalance(i));
    }
  }
}
