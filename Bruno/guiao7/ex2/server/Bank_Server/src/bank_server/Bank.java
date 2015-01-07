package bank_server;

import java.util.concurrent.locks.*;
import java.util.*;
import util.*;

class Account {

    private float balance;
    private Lock l;

    public Account(float balance) {
        this.balance = balance;
        l = new ReentrantLock();
    }

    public void withdraw(float amount) throws NotEnoughFunds {
        if (amount > balance) {
            throw new NotEnoughFunds();
        } else {
            balance -= amount;
        }
    }

    public void deposit(float amount) {
        balance += amount;
    }

    public float getBalance() {
        return balance;
    }

    public void lock() {
        l.lock();
    }

    public void unlock() {
        l.unlock();
    }
}

public class Bank implements BankInterface {

    Hashtable<Integer, Account> accts = new Hashtable<Integer, Account>();
    Lock l = new ReentrantLock();
    int nextAcc;

    @Override
    public int createAccount(float initialBalance) {
        l.lock();
        try {
            nextAcc++;
            int id = nextAcc;
            accts.put(id, new Account(initialBalance));
            return id;
        } finally {
            l.unlock();
        }
    }

    private Account getAccount(int id) throws InvalidAccount {
        Account c = accts.get(id);
        if (c == null) {
            throw new InvalidAccount();
        }
        return c;
    }

    @Override
    public float closeAccount(int id) throws InvalidAccount {
        Account c;
        l.lock();
        try {
            c = getAccount(id);
            accts.remove(id);
            c.lock();
        } finally {
            l.unlock();
        }
        try {
            return c.getBalance();
        } finally {
            c.unlock();
        }
    }

    @Override
    public void transfer(int from, int to, float amount) throws InvalidAccount, NotEnoughFunds {
        Account s, d;

        l.lock();
        try {
            s = accts.get(from);
            d = accts.get(to);
            if (from < to) {
                s.lock();
                d.lock();
            } else {
                d.lock();
                s.lock();
            }
        } finally {
            l.unlock();
        }
        try {
            s.withdraw(amount);
            d.deposit(amount);
        } finally {
            s.unlock();
            d.unlock();
        }
    }

    @Override
    public float totalBalance(int ids[]) throws InvalidAccount {
        Account[] accs = new Account[ids.length];
        int[] ordIds = ids.clone();
        Arrays.sort(ordIds);
        l.lock();
        try {
            for (int i = 0; i < ids.length; i++) {
                accs[i] = getAccount(ids[i]);
            }
            for (int i = 0; i < ids.length; i++) {
                accs[i].lock();
            }
        } finally {
            l.unlock();
        }
        float total = 0;
        for (int i = 0; i < ids.length; i++) {
            total += accs[i].getBalance();
            accs[i].unlock();
        }

        return total;
    }

    @Override
    public void withdraw(int id, float amount) throws NotEnoughFunds {
        Account a = accts.get(id);
        a.lock();
        try {
            a.withdraw(amount);
        } finally {
            a.unlock();
        }
    }

    @Override
    public void deposit(int id, float amount) {
        Account a = accts.get(id);
        a.lock();
        try {
            a.deposit(amount);
        } finally {
            a.unlock();
        }
    }

    @Override
    public float getBalance(int id) {
        Account a = accts.get(id);
        a.lock();
        try {
            return a.getBalance();
        } finally {
            a.unlock();
        }
    }
}
