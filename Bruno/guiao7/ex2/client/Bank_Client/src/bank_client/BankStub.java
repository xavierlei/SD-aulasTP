package bank_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import util.*;

class BankStub implements BankInterface {
    
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    public BankStub(Socket socket, BufferedReader in, PrintWriter out) {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }

    @Override
    public int createAccount(float initialBalance) throws IOException{
        String msg = "register:" + initialBalance;
        
        out.println(msg);
        out.flush();
        
        int acct = Integer.parseInt(in.readLine());
        
        return acct;
    }

    @Override
    public float closeAccount(int id) throws InvalidAccount, IOException {
        String msg = "close:" + id;
        
        out.println(msg);
        out.flush();
        
        String response = in.readLine();
        if(response.equals("-1"))
            throw new InvalidAccount();
        
        return Float.parseFloat(response);
    }

    @Override
    public void transfer(int from, int to, float amount) throws InvalidAccount, NotEnoughFunds, IOException {
        String msg = "transfer:" + from + ":" + to + ":" + amount;
        
        out.println(msg);
        out.flush();
        
        String response = in.readLine();
        if(response.equals("-1"))
            throw new InvalidAccount();
        if(response.equals("-2"))
            throw new NotEnoughFunds();
    }

    @Override
    public float totalBalance(int[] ids) throws InvalidAccount, IOException {
        String msg = "balance2:";
        
        for(int i = 0; i < ids.length; i++) {
            msg += ids[i];
            msg += ":";
        }
        
        out.println(msg);
        out.flush();
        
        String response = in.readLine();
        if(response.equals("-1"))
            throw new InvalidAccount();
            
        return Float.parseFloat(response);
    }

    @Override
    public void withdraw(int id, float amount) throws NotEnoughFunds, IOException {
        String msg = "withdraw:" + id + ":" + amount;
        
        out.println(msg);
        out.flush();
        
        String response = in.readLine();
        if(response.equals("-2"))
            throw new NotEnoughFunds();
    }

    @Override
    public void deposit(int id, float amount) throws IOException {
        String msg = "deposit:" + id + ":" + amount;
        
        out.println(msg);
        out.flush();
    }

    @Override
    public float getBalance(int id) throws IOException {
        String msg = "balance1:" + id;
        
        out.println(msg);
        out.flush();
        
        String response = in.readLine();
        
        System.out.println("Response: " + response);
        
        if(response.equals(""))
            return 999;
        
        float balance = Float.parseFloat(response);
        
        return balance;
    }
    
    public String parseAndSend(String message) {
        String ret = "";
        
        if(message.equals(""))
            return null;
        
        String[] argv = message.split(" ");
        String command = argv[0];
        
        switch(command) {
            case "register":
                try {
                    ret = "Account nr: " + createAccount(Float.parseFloat(argv[1]));
                } catch (IOException ex) {
                    ret = "Connection lost!";
                }
                break;
            case "close":
                try {
                    ret = "Account closed! Balance: " + closeAccount(Integer.parseInt(argv[1]));
                } catch (InvalidAccount ex) {
                    ret = "Invalid Account!";
                } catch (IOException ex) {
                    ret = "Connection lost!";
                }
                break;
            case "transfer":
                try {
                    transfer(Integer.parseInt(argv[1]),Integer.parseInt(argv[2]),Float.parseFloat(argv[3]));
                } catch (InvalidAccount ex) {
                    ret = "Invalid Account!";
                } catch (NotEnoughFunds ex) {
                    ret = "Not enough funds!";
                } catch (IOException ex) {
                    ret = "Connection lost";
                }
                break;
            case "balance":
                if(argv.length < 3)
                    try {
                        ret = "Balance: " + Float.toString(getBalance(Integer.parseInt(argv[1])));
                    } catch (IOException ex) {
                        ret = "Connection lost!";
                    }
                else {
                    int[] accts = new int[argv.length - 1];
                    for(int i = 1; i < argv.length; i++) 
                        accts[i - 1] = Integer.parseInt(argv[i]);
                    try {
                        ret = "Balance: " + Float.toString(totalBalance(accts));
                    } catch (InvalidAccount ex) {
                        ret = "Invalid Account!";
                    } catch (IOException ex) {
                        ret = "Connection lost!";
                    }
                }
                break;
            case "withdraw":
                try {
                    withdraw(Integer.parseInt(argv[1]),Float.parseFloat(argv[2]));
                } catch (NotEnoughFunds ex) {
                    ret = "Not enough funds!";
                } catch (IOException ex) {
                    ret = "Connection lost!";
                }
                break;
            case "deposit":
                try {
                    deposit(Integer.parseInt(argv[1]),Float.parseFloat(argv[2]));
                } catch (IOException ex) {
                    ret = "Connection lost!";
                }
                break;
            default:
                ret = command + " not available!";
                break;
        }
        return ret;
    }
}
