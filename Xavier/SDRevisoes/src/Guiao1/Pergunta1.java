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
public class Pergunta1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        int N = 100;
        int I = 10;
        Incrementador [] arr = new Incrementador[N];
        for(int i = 0; i < N; i++){
            arr[i] = new Incrementador(I);
            arr[i].start();
        }
        for(int i = 0; i < N; i++)
            arr[i].join();
    }
    
}
