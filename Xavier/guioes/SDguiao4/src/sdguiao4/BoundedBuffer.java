/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao4;

import static java.lang.Thread.sleep;
import java.util.LinkedList;


/**
 *
 * @author xavier
 */
public class BoundedBuffer {
    private int size;
    private LinkedList list;
    
    public BoundedBuffer(){
        size = 10;
        list = new LinkedList();
    }
    
    public synchronized Object get() throws InterruptedException{
        while(list.isEmpty())
            wait();
        System.out.println("produção");
        sleep(1000);
        notifyAll();
        return list.remove();
    }
    
    public synchronized void put(Object o) throws InterruptedException{
        while(list.size() == size)
            wait();
        sleep(1000);
        list.add(o);
    notifyAll();
    }
}
