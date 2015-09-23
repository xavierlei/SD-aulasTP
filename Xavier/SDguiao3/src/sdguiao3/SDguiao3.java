/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao3;

import java.util.Iterator;

/**
 *
 * @author xavier
 */
public class SDguiao3 {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Banco b = new Banco();
        Integer [] k = new Integer[10];
        ContaThread [] t;
        t = new ContaThread[100];
        int i;
        for(i = 0; i < 10; i++ ){
            b.createAccount(1000);
        }
        Iterator<Integer> it = b.getContas().iterator();
        i = 0;
        while(it.hasNext()){
            k[i] = it.next();
            i++;
        }
        for(i = 0; i < 100; i++){
            t[i] = new ContaThread(b,k[i%10]);
            t[i].start();
        }
        for(i = 0; i < 100; i++){
            t[i].join();
        }
        b.print();
    }   
}


