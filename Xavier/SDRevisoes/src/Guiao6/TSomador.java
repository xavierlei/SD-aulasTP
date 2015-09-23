/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author xavier
 */
public class TSomador extends Thread {
    Socket s;
    public TSomador(Socket s){
        this.s = s;
    }
    @Override
    public void run() {
        try{
            int soma = 0;
                BufferedReader i = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter o = new PrintWriter(s.getOutputStream());
                String l = i.readLine();
                while(l != null){
                    soma+=new Integer(l).intValue();
                    System.out.println("soma: "+soma);
                    l = i.readLine();
                }
                System.out.println("cliente quer resultado");
                o.println(soma);
                o.flush();
                s.close();
        }catch(IOException e){}
    }
}
