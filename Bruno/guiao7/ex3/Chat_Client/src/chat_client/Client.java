/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author bruno
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        class Listener extends Thread {
            private final Socket socket;
            
            public Listener(Socket socket) { this.socket = socket; }
            
            @Override
            public void run() {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    while(true) {
                        String str = in.readLine();
                        if(str == null)
                            break;
                        System.out.println(str);
                    }
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        class Writer extends Thread {
            private final Socket socket;
            
            public Writer(Socket socket) { this.socket = socket; }
            
            @Override
            public void run() {
                try (
                        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                        ) {
                    while(true) {
                        String str = stdin.readLine();
                        if(str == null)
                            break;
                        out.println(str);
                        out.flush();
                    }
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        try {
            Socket socket = new Socket("localhost",50000);
            new Listener(socket).start();
            new Writer(socket).start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
