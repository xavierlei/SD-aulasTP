/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao4;

import java.util.LinkedList;

/**
 *
 * @author xavier
 */
public class BoundedBufferSync {
    int size;
    LinkedList list;
    int N;
    
    
    public BoundedBufferSync(int size, int N){
        this.size = size;
        this.list = new LinkedList();
        this.N = N;
    }
    
    public synchronized void put(int v) throws InterruptedException{
        while(list.size()==size)
            wait();
        list.add(v);
        notifyAll();
    }
    
    public synchronized int get() throws InterruptedException{
        while(list.isEmpty())
            wait();
        int res = (int) list.getFirst();
        notifyAll();
        return res;
    }
    
    
}
