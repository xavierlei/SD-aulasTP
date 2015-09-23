/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao4;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author xavier
 */
public class BoundedBuffer {
    public ArrayList<Integer> array;
    int size;
    int used;
    ReentrantLock l;
    Condition cEmpty;
    Condition cFull;
    public BoundedBuffer(int size){
        this.array =new ArrayList<Integer>();
        l = new ReentrantLock();
        cEmpty = l.newCondition();
        cFull = l.newCondition();
        this.size = size;
        this.used = 0;
    }
    
    public void put(int v) throws InterruptedException{
        l.lock();
        try{
            while(used >= size){
                cFull.await();
            }
            array.add(v);
            used ++;
            cEmpty.signalAll();
        }
        finally{
            l.unlock();
        }
    }
    
    public int get() throws InterruptedException{
        l.lock();
        try{
            while(array.isEmpty()){
                cEmpty.await();
            }
            int res = array.get(0);
            used --;
            cFull.signalAll();
            return res;
        }
        finally{
            l.unlock();
        }
    }
    
}
