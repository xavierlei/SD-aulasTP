/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao7Perg1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author xavier
 */
public class Server {
    public static void main(String [] args) throws IOException{
        ServerSocket ss = new ServerSocket(1234);
        Contador c = new Contador();
        while(true){
            Socket s = ss.accept();
            TSum t = new TSum(s,c);
            t.start();
        }
    }
}
