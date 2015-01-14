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
    private int N;
    private int cont;
    private int eflag;
    
    public Barreira(){
        cont = 0;
        eflag = 0;
        N = 20;
    }
    
    public synchronized void esperar() throws InterruptedException{
        int tag;
        cont++;
        tag = cont;
        if((cont%N) == 0)
            notifyAll();
        else {
            System.out.println("cont: "+cont);
            while(cont < (tag+N)-1){
                wait();
            }
        }
    }
    
}



























/*public synchronized void esperar() throws InterruptedException{
        int entrada = fase;
        espera ++;
        if(espera == N){
            espera = 0;
            fase++;
            notifyAll();
        }
        else{
            while(entrada == fase){
                wait();
            }
        }
    }*/