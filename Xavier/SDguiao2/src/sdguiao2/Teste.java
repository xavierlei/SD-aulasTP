/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao2;

import java.util.Random;

/**
 *
 * @author xavier
 */
public class Teste extends Thread {
    private static Banco2 b = new Banco2();
    private static Random r = new Random();
    public static final int MAX = 10;
    
    @Override
    public void run(){
        for(int i = 0; i < 100; i++){
            b.credito(r.nextInt(MAX),1);
            b.debito(r.nextInt(MAX),1);
            b.transferir(r.nextInt(MAX),r.nextInt(MAX),1);
        }
    }
    public static void main(String[] args) throws InterruptedException{
        Thread t[] = new Thread [100];
        for(int i = 0; i < 100; i ++){
            t[i] = new Teste();
            t[i].start();
        }
        for(int i = 0; i < 100; i ++){
            t[i].join();
        }
        b.print();
    }
    
}
