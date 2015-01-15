/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pergunta1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author xavier
 */
public class MultiserverConcorente {
    public static void main(String [] args) throws IOException{
        boolean b = true;
        Contador total = new Contador();
        ThreadSomador t;
        ServerSocket ss = new ServerSocket(12345);
        System.out.println("###MultiServer running###");
            while(b){
                System.out.println("waiting for client");
                Socket s = ss.accept();
                t = new ThreadSomador(s,total);
                t.start();
            }
            ss.close();
    }
}
