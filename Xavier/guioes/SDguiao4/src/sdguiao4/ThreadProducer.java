package sdguiao4;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xavier
 */
public class ThreadProducer extends Thread {
    public BoundedBuffer b;
    private int id;
    public Barreira barreira;
    
    public ThreadProducer(int id, BoundedBuffer buff,Barreira barr){
        b = buff;
        this.id = id;
        barreira = barr;
    }
    @Override
    public void run(){
        
        try{
            barreira.esperar();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true){
            try{
            b.put("Produto");
            }catch(InterruptedException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
