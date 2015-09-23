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
public class RWLock {
    public ReentrantLock l;
    private Condition cR;
    private Condition cW;
    private int readers;
    private int writers;
    int vez;
    int atendidos;
    
    public RWLock(){
        l = new ReentrantLock();
        cR = l.newCondition();
        cW = l.newCondition();
        readers = 0;
        writers = 0;
        vez = 0;
        atendidos = 0;
    }
    
    public void Rlock() throws InterruptedException{
        l.lock();
        try{
            int senha = vez;
            vez++;
            while(writers >0 && senha > atendidos)
                cR.await();
            readers++;
            atendidos ++;
        }
        finally{
            l.unlock();
        }       
    }
    
    public void RUnlock(){
        l.lock();
        try{
            readers --;
            cW.signalAll();
        }
        finally{
            l.unlock();
        }
    }
    
    public void WLock() throws InterruptedException{
        l.lock();
        try{
            int senha = vez;
            vez++;
            while((readers > 0 || writers > 0) && senha > atendidos)
                cW.await();
            writers ++;
            atendidos ++;
        }
        finally{
            l.unlock();
        }
    }
    
    public void WUnlock(){
        l.lock();
        try{
            writers --;
            cR.signalAll();
            cW.signalAll();
        }
        finally{
            l.unlock();
        }
    }
    
}
