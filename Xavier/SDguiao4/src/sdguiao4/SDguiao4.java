/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao4;

/**
 *
 * @author xavier
 */
public class SDguiao4 {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        int i;
        Barreira barreira = new Barreira();
        BoundedBuffer b = new BoundedBuffer();
        ThreadConsumer [] c = new ThreadConsumer[10];
        ThreadProducer [] p = new ThreadProducer[10];
        int nc = 10;
        int np = 10;
        
        for(i = 0; i < nc; i++){
            c[i] = new ThreadConsumer(i,b,barreira);
            c[i].start();
        }
        for(i = 0; i < np; i++){
            p[i] = new ThreadProducer(i,b,barreira);
            p[i].start();
        }
        for(i = 0; i < nc; i++){
            c[i].join();
        }
        for(i = 0; i < np; i++){
            p[i].join();
        }
    }
    
}
