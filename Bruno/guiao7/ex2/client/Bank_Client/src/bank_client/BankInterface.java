package bank_client;

import java.io.IOException;
import util.*;

public interface BankInterface {

  public int createAccount(float initialBalance) throws IOException;

  public float closeAccount(int id) throws InvalidAccount, IOException;

  public void transfer(int from, int to, float amount) throws InvalidAccount, NotEnoughFunds, IOException;

  public float totalBalance(int ids[]) throws InvalidAccount, IOException;

  public void withdraw(int id, float amount) throws NotEnoughFunds, IOException;

  public void deposit(int id, float amount) throws IOException;

  public float getBalance(int id) throws IOException;
}
