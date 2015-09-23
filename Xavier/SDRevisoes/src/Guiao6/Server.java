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
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author xavier
 */
public class Server {
    public static void main(String [] args) throws IOException{
        ServerSocket ss;
        ss = new ServerSocket(1234);
        while(true){
            Socket s = ss.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream());
            String st;
            st = in.readLine();
            while(st != null){
                System.out.println("cliente enviou: "+st);
                out.println("server says: "+st);
                out.flush();
                st = in.readLine();
            }
            s.close();
        }
    }
}
