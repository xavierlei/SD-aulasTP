/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao5;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author xavier
 */
public class BoundedBuffer2 {
    
    ReentrantLock lock;
    int size;
    LinkedList list;
    Condition cg;
    Condition cp;
    
    BoundedBuffer2(int n){
        lock = new ReentrantLock();
        size = n;
        list = new LinkedList();
        cg = lock.newCondition();
        cp = lock.newCondition();
    }
    
    public Object get() throws InterruptedException{
        Object res;
        lock.lock();
        try{
            while(list.isEmpty())
                cg.await();
            res = list.remove();
            cp.signal();
            return res;
        }
        
        finally{
            lock.unlock();
        }
    }
    
    public void put(Object o) throws InterruptedException{
        lock.lock();
        try{
            while(list.size() >= this.size)
                cp.await();
            this.list.add(o);
            cg.signal();
        }
        finally{
            lock.unlock();
        }
    }
    
}
