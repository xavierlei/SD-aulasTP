/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao1;

import Guiao1.Counter;

/**
 *
 * @author xavier
 */
public class Incrementador2 extends Thread{
    private Counter contador;
    private int I;
    public Incrementador2(Counter c, int I){
        this.contador = c;
        this.I = I;
    }
    public void run(){
        for(int i = 0; i < I; i++){
            this.contador.increment();
            //this.contador.c ++;
            System.out.println(this.contador.c);
        }
    }
}
