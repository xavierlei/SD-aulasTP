/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author xavier
 */
public class RWLock {
    private int leitores;
    private int escritores;
    Condition r;
    Condition w;
    private ReentrantLock l;
    private int contador;
    private int vez;
    
    public RWLock(){
        leitores = 0;
        escritores = 0;
        l = new ReentrantLock();
        r = l.newCondition();
        w = l.newCondition();
        contador = 0;
        vez = 0;
    }
    
    public void readLock() throws InterruptedException{
        l.lock();
        int senha = vez;
        vez ++;
        while(escritores > 0 && senha > contador)
            r.await();
        try{
           leitores++; 
           contador++;
        }
        finally{
            l.unlock();
        }
    }
    
    public void readUnlock(){
        l.lock();
        try{
            leitores--;
            w.signalAll();
        }
        finally{
            l.unlock();
        }
    }
    
    public void writeLock() throws InterruptedException{
        l.lock();
        int senha = vez;
        vez ++;
        while((leitores > 0 || escritores > 0) && senha > contador)
            w.await();
        try{
            escritores++;
            contador++;
        }
        finally{
            l.unlock();
        }
    }
    
    public void writeUnlock(){
        l.lock();
        try{
            escritores--;
            r.signalAll();
            w.signalAll();
        }
        finally{
            l.unlock();
        }
    }
}

/*
*Evitar starvation, por exemplo, na situação de haver muitos leitores e poucos escritoress
*/