/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author xavier
 */
public class Warehouse {
    private HashMap<String,Item> itens;
    public ReentrantLock l;
    private Condition c;
    
    public Warehouse(){
        itens = new HashMap<String,Item>();
        l = new ReentrantLock();
        c = l.newCondition();
    }
    
    public void suply(String item, int quantity) throws InterruptedException{
        l.lock();
        try{
            if(!itens.containsValue(item)){
                itens.put(item, new Item(item,quantity));
                c.signalAll();
            }
            else{
                itens.get(item).suply(quantity);
            }
        }
        finally{
            l.unlock();
        }
        
    }
    
    public void consume(String [] items) throws InterruptedException{
        l.lock();
        try{
            int s = items.length;
            for(int i = 0; i < s; i++){
                if(!itens.containsKey(items[i]))
                    itens.put(items[i],new Item(items[i],10));
                itens.get(items[i]).l.lock();
            }
        }
        finally{
            l.unlock();
        }
        try{
            int s = items.length;
            for(int i = 0; i < s; i++)
                itens.get(items[i]).get();
        }
        finally{
            int s = items.length;
            for(int i = 0; i < s; i++)
                itens.get(items[i]).l.unlock();
        }
    }
}
