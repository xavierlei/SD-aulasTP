/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiao3;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author xavier
 */
public class Banco implements Bank {
    
    private HashMap<Integer,Conta> contas;
    private ReentrantLock l;
    private Integer contador;
    public Banco(){
        this.contas = new HashMap<Integer,Conta>();
        l = new ReentrantLock();
        contador = 0;
    }

    @Override
    public int createAccount(float initialBalance) {
        this.l.lock();
        try{
            Conta c = new Conta(contador,initialBalance);
            contas.put(contador, c);
            contador ++;
        }
        finally{
            this.l.unlock();
        }
        return (contador - 1);
    }

    @Override
    public float closeAccount(int id) throws InvalidAccount {
        l.lock();
        float s = 0;
        try{
            if(!contas.containsKey(s))
                throw new InvalidAccount("Conta não existe");
            s = contas.get(id).saldo();
            contas.remove(s);
            return s;
        }
        finally{
            l.unlock();
        }
    }

    @Override
    public void transfer(int from, int to, float amount) throws InvalidAccount, NotEnoughFunds {
        l.lock();
        Conta o;
        Conta d;
        try{
            if(!contas.containsKey(to))
                throw new InvalidAccount("conta destino inválida");
            if(!contas.containsKey(from))
                throw new InvalidAccount("conta origem inválida");
            o = contas.get(from);
            d = contas.get(to);
            o.l.lock();
            d.l.lock();
        }
        finally{
            l.unlock();
        }
        try{
            if(o.saldo() < amount)
                throw new NotEnoughFunds("sem fundos");
            o.levanta(amount);
            d.deposita(amount);
        }
        finally{
            o.l.unlock();
            d.l.unlock();
        }  
    }
    
    

    @Override
    public float totalBalance(int[] accounts) {
        int size = accounts.length;
        float total = 0;
        l.lock();
        try{
            for(int i = 0; i < size; i++){
                if(contas.containsKey(accounts[i])){
                    total += contas.get(accounts[i]).saldo();
                }
            }
            return total;
        }
        finally{
            l.unlock();
        }
    }
    
}
