/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author xavier
 */
public class Banco implements Bank {
    private Map<Integer,Conta> b;
    private int cod;
    private ReentrantLock bl = new ReentrantLock();
    public Banco(){
        b = new HashMap<Integer,Conta>();
        cod = 0;
    }
    
    
    public void deposito(int n, float q) throws InvalidAccount{
            bl.lock();
            if(!b.containsKey(n)){
                bl.unlock();
                throw new InvalidAccount("Conta não existe!!!");
            }
            Conta c = b.get(n);
            c.l.lock();
            bl.unlock();
            c.deposito(q);
            c.l.unlock();
    }
    public void levantamento(int id, float val) throws InvalidAccount{
        Conta c;
            bl.lock();
            if(!b.containsKey(id)){
                bl.unlock();
                throw new InvalidAccount("Conta não existe!!!");
            }
            c = b.get(id);
            c.l.lock();
            bl.unlock();
            c.levantamento(val);
            c.l.unlock();
    }
    
    @Override
    public int createAccount(float initialBalance) {
     try{
         bl.lock();
         Conta c = new Conta();
         c.deposito(initialBalance);
         b.put(cod, c);
         cod++;
         return cod-1;
     }
     finally{
         bl.unlock();
     }
     
    }

    @Override
    public float closeAccount(int id) throws InvalidAccount {
        Integer c;
        float res;
        c = id;
        Conta temp;
            bl.lock();
            if(!b.containsValue(c)){
                bl.unlock();
                throw new InvalidAccount("Conta não existe!!!");
            }
            temp = b.remove(id);
            temp.l.lock();
            bl.unlock();
            res = temp.saldo();
            temp.l.unlock();
            return res;
    }

    @Override
    public void transfer(int from, int to, float amount) throws InvalidAccount, NotEnougthFunds {
        Conta c1;
        Conta c2;
            bl.lock();
            if(!b.containsKey(from)){
                bl.unlock();
                throw new InvalidAccount("Conta de origem não existe!!!");
            }
            if(!b.containsKey(to)){
                bl.unlock();
                throw new InvalidAccount("Conta de destino não existe!!!");
            }
            c1 = b.get(from);
            c2 = b.get(to);
            c1.l.lock();
            if(c1.saldo() < amount){
                c1.l.unlock();
                bl.unlock();
                throw new NotEnougthFunds("Não tem saldo suficiente!!!");
            } 
            c2.l.lock();
            bl.unlock();
            c1.levantamento(amount);
            c2.deposito(amount);
            c1.l.unlock();
            c2.l.unlock();
}

    /**
     *
     * @param accounts
     * @return
     * @throws InvalidAccount
     */
    @Override
    public float totalBalance(int[] accounts) throws InvalidAccount{
        float total = 0;
        int l = accounts.length;
        for(int i = 0; i < l; i++){
            bl.lock();
            if(!b.containsKey(accounts[i])){
                bl.unlock();
                throw new InvalidAccount("pelo menos uma das contas não Existe!!!");
            }
            Conta c = b.get(accounts[i]);
            c.l.lock();
            bl.unlock();
           total += c.saldo();
           c.l.unlock();
        }
        return total;
    }
    public Set<Integer> getContas(){
        return b.keySet();
    }
    public void print(){
        for(Integer k : b.keySet()){
            System.out.println("chave: "+k+" saldo: "+b.get(k).saldo());
        }
    }
}
