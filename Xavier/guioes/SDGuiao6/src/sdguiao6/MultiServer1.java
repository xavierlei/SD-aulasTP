package sdguiao6;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xavier
 */
public class MultiServer1 {
    public static void main(String [] args) throws IOException{
        boolean b = true;
        ThreadServer t;
        ServerSocket ss = new ServerSocket(12345);
        System.out.println("###MultiServer running###");
            while(b){
                System.out.println("waiting for client");
                Socket s = ss.accept();
                t = new ThreadServer(s);
                t.start();
            }
            ss.close();
    }

}
