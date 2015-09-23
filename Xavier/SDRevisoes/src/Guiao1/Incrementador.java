/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao1;

/**
 *
 * @author xavier
 */
public class Incrementador extends Thread{
    private int I;
    public Incrementador(int c){
        this.I = c;
    }
    
    @Override
    public void run(){
        for(int i = 0; i < I; i++)
                System.out.println(i);
    }
}
