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
public class Pergunta2 {
    public static void main(String[] arg) throws InterruptedException{
        Counter contador = new Counter();
        int I = 1000;
        int N = 100;
        Incrementador2 [] arr = new Incrementador2[N];
        for(int i = 0; i < N; i++){
            arr[i] = new Incrementador2(contador,I);
            arr[i].start();
        }
        for(int i =0 ; i < N; i++){
            arr[i].join();
            System.out.println("contagem: "+contador.c);
        }
    }
    
}
