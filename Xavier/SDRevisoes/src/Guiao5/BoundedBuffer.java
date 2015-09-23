/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao5;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author xavier
 */
public class BoundedBuffer {
    public ReentrantLock l;
    private Condition full;
    private Condition empty;
    private LinkedList buffer;
    private int size;
    
    public BoundedBuffer(int size){
        l = new ReentrantLock();
        full = l.newCondition();
        empty = l.newCondition();
        this.size = size;
    }
    
    public void put(int v) throws InterruptedException{
        l.lock();
        try{
            while(buffer.size() == size)
                full.await();
            buffer.add(v);
            empty.signalAll();
        }
        finally{
            l.unlock();
        }
    }
    public int get() throws InterruptedException{
        l.lock();
        try{
            while(buffer.isEmpty())
                empty.await();
            int v = (int) buffer.getFirst();
            full.signalAll();
            return v;
        }
        finally{
            l.unlock();
        }
    }
}
