package bank_server;

import util.*;

interface BankInterface {

  public int createAccount(float initialBalance);

  public float closeAccount(int id) throws InvalidAccount;

  public void transfer(int from, int to, float amount) throws InvalidAccount, NotEnoughFunds;

  public float totalBalance(int ids[]) throws InvalidAccount;

  public void withdraw(int id, float amount) throws NotEnoughFunds;

  public void deposit(int id, float amount);

  public float getBalance(int id);
}
