/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xavier
 */
public class ThreadConsumer extends Thread{
    
    public BoundedBuffer b;
    private int id;
    public Barreira barreira;
    
    public ThreadConsumer(int id, BoundedBuffer buff,Barreira barr){
        b = buff;
        this.id = id;
        barreira = barr;
    }
    @Override
    public void run(){
        barreira.esperar();
        while(true){
            try {
            System.out.println("consumer "+id + ": "+b.get().toString());
            }catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }  
    }
}
