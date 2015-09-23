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
public class Somador {
    public static void main(String [] args) throws IOException{
        ServerSocket ss = new ServerSocket(1234);
        while(true){
            Socket s = ss.accept();
            System.out.println("client accepted");
            TSomador t = new TSomador(s);
            t.start();
        }
    }
}
