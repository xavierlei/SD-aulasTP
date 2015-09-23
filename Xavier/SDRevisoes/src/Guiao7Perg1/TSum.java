/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao7Perg1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author xavier
 */
public class TSum extends Thread {
    Socket s;
    Contador c;
    
    public TSum(Socket s, Contador c){
        this.s = s;
        this.c=c;
    }
    
    @Override
    public void run() {
        try{
                BufferedReader i = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter o = new PrintWriter(s.getOutputStream());
                c.l.lock();
                try{
                    c.ps.add(o);
                }
                finally{
                    c.l.unlock();
                }
                String l = i.readLine();
                while(l != null){
                    c.l.lock();
                    try{
                        c.soma+=new Integer(l).intValue();
                        o.println("soma: "+c.soma);
                        o.flush();
                    }
                    finally{
                        c.l.unlock();
                    }
                    System.out.println("soma: "+c.soma);
                    l = i.readLine();
                }
                System.out.println("cliente quer resultado");
                c.l.lock();
                try{
                    for(PrintWriter pw : c.ps){
                        pw.println(c.soma);
                        pw.flush();
                    }
                }
                finally{
                    c.l.unlock();
                }
        }catch(IOException e){}
    }
    
    
}
