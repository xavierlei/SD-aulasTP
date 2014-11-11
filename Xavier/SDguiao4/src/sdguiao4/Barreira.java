/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao4;

/**
 *
 * @author xavier
 */
public class Barreira {
    public int N;
    private int cont;
    
    public Barreira(){
        cont = 0;
        N = 20;
    }
    
    public synchronized void esperar() throws InterruptedException{
        cont++;
        System.out.println("cont: "+cont);
        if(cont == N)
            notifyAll();
        else 
            wait();
    }
}
