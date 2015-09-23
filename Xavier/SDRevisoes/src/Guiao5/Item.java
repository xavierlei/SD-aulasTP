/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author xavier
 */
public class Item {
    public ReentrantLock l;
    private Condition c;
    private String item;
    private int quantity;

    
    public Item(String item, int quantity){
        this.l = new ReentrantLock();
        this.c = l.newCondition();
        this.item = item;
        this.quantity = quantity;
    }
    
    public void suply(int v) throws InterruptedException{
            this.quantity += v;
            c.signalAll();
    }
    
    public void get() throws InterruptedException{
            while(quantity == 0)
                c.await();
            quantity --;
    }
}
