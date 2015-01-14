/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pergunta1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xavier
 */
public class ThreadSomador extends Thread {
    
    private Socket s;
    private Contador c;
    public ThreadSomador(Socket s, Contador c){
        this.s = s;
        this.c = c;
    }
    
    @Override
    public void run(){
        Integer num;
        int media;
        System.out.println("Client beeing served");
        try{
            PrintWriter o = new PrintWriter(s.getOutputStream());
            BufferedReader i = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String l = i.readLine();
            while(l!=null){
                System.out.println("Server Listening");
                System.out.println("Client says: "+l);
                num = new Integer(l);
                c.l.lock();
                o.println("server says: "+ c.sum(num.intValue()));
                c.l.unlock();
                o.flush();
                l = i.readLine();
            }
            c.l.lock();
            try {
                media = c.media();
            } finally {
                c.l.unlock();
            }
            o.println("server says: "+ media);
            o.flush();
            System.out.println("client just leaved");
            s.close();
        }
        catch(IOException ex){
            
        }
       
    }
}
