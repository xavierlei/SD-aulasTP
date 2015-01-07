package bank_server;

import util.*;

public class BankSkeleton implements BankInterface {
    
    Bank bank;
    
    public BankSkeleton(Bank bank) {
        this.bank = bank;
    }

    @Override
    public int createAccount(float initialBalance) {
        return this.bank.createAccount(initialBalance);
    }

    @Override
    public float closeAccount(int id) throws InvalidAccount {
        return this.bank.closeAccount(id);
    }

    @Override
    public void transfer(int from, int to, float amount) throws InvalidAccount, NotEnoughFunds {
        this.bank.transfer(from, to, amount);
    }

    @Override
    public float totalBalance(int[] ids) throws InvalidAccount {
        return this.bank.totalBalance(ids);
    }

    @Override
    public void withdraw(int id, float amount) throws NotEnoughFunds {
        this.bank.withdraw(id, amount);
    }

    @Override
    public void deposit(int id, float amount) {
        this.bank.deposit(id, amount);
    }

    @Override
    public float getBalance(int id) {
        return this.bank.getBalance(id);
    }
    
    public String parseAndCall(String message) {
        String ret = "";
        
        if(message.equals(""))
            return null;
        
        String[] argv = message.split(":");
        String command = argv[0];
        
//        for(int i = 0; i < argv.length; i++)
//            System.out.println(argv[i]);
        
        switch(command) {
            case "register":
                ret = Integer.toString(createAccount(Float.parseFloat(argv[1])));
                break;
            case "close":
                try {
                    ret = Float.toString(closeAccount(Integer.parseInt(argv[1])));
                } catch (InvalidAccount ex) {
                    ret = "-1";
                }
                break;
            case "transfer":
                try {
                    transfer(Integer.parseInt(argv[1]),Integer.parseInt(argv[2]),Float.parseFloat(argv[3]));
                } catch (InvalidAccount ex) {
                    ret = "-1";
                } catch (NotEnoughFunds ex) {
                    ret = "-2";
                }
                break;
            case "balance1":
                ret = Float.toString(getBalance(Integer.parseInt(argv[1])));
                System.out.println("Ret -> " + ret);
                break;
            case "balance2":
                int[] accts = new int[argv.length - 1];
                for(int i = 1; i < argv.length; i++) 
                    accts[i - 1] = Integer.parseInt(argv[i]);
                try {
                    ret = Float.toString(totalBalance(accts));
                } catch (InvalidAccount ex) {
                    ret = "-1";
                }
                break;
            case "withdraw":
                try {
                    withdraw(Integer.parseInt(argv[1]), Float.parseFloat(argv[2]));
                } catch (NotEnoughFunds ex) {
                    ret = "-2";
                }
                 break;
            case "deposit":
                deposit(Integer.parseInt(argv[1]), Float.parseFloat(argv[2]));
                break;
            default:
                break;
        }
        
        return ret;
    }
}