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
public class Main2 {
    public static void main(String[] args) throws InterruptedException{
        int i;
        Counter c = new Counter();
        CountThread [] t;
        t = new CountThread[10];
        for(i = 0; i < 10; i ++){
            t[i] = new CountThread(c);
            t[i].start();
        }
        for(i = 0; i < 10; i++){
            t[i].join();
            
        }
        System.out.println(c.getC());
    }
    /*
    *Conclusão: ha incrementos que se perdem devido a concorência. enquanto uma thread vai
    *a memoria buscar o valor e actualiza-o, outra thread tb faz o mesmo acabando por haver incoerências
    *nos incrementos
    */
}
