/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao1;

import java.util.ArrayList;

/**
 *
 * @author xavier
 */
public class SDguiao1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException   {
        int i;
        int n = 10;
        Thread [] t;
        t = new Thread[10];
        for(i = 0; i < n; i++){
            Tarefa r = new Tarefa(i);
            t[i] = new Thread(r);
            t[i].start();
        }
        for(i = 0; i < n; i++){ 
            t[i].join();
        }
    }
    
}
