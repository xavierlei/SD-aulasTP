/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao2;

/**
 *
 * @author xavier
 */
public class Main4 {
    public static void main(String[] args) throws InterruptedException{
        int i;
        Banco2 bank = new Banco2();
        ClienteThread2 [] listaC;
        listaC = new ClienteThread2[100];
        for(i = 0; i < 100; i++){
            listaC[i] = new ClienteThread2(bank,i);
            listaC[i].start();
        }
        for(i = 0; i < 100; i++){
            listaC[i].join();
        }
    }
}
