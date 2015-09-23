/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao4;

/**
 *
 * @author xavier
 */
public class Barreira {
    int vez = 0;
    int contagem = 0;
    int sair = 0;
    int total = 0;
    boolean aberto  = false;
    int N;
    
    public Barreira(int N){
        this.N = N;
    }
    
    public synchronized void barreira() throws InterruptedException{
        int senha = vez;
        vez ++;
        while(!aberto || senha > contagem)
            wait();
        notifyAll();
        contagem ++;
        total ++;
        while(total % N != 0)
            wait();
        aberto = false;
        notifyAll();
        sair ++;
        if(sair%N == 0)
            aberto = true;
    }
}
