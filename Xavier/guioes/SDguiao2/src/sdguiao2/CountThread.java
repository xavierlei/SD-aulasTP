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
public class CountThread extends Thread {
    private Counter c;
    public CountThread(Counter c){
        this.c = c;
    }
    public void run(){
        cont();
    }
    public void cont(){
        int i;
        for(i=0; i < 1000; i++)
           // this.c.c = this.c.c + 1;
            this.c.increment();
            System.out.println(this.c.getC());
    }
    
}
