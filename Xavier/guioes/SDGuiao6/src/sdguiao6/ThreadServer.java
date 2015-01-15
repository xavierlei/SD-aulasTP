package sdguiao6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xavier
 */
public class ThreadServer extends Thread {
    private Socket s; 
    
    public ThreadServer(Socket s){
        this.s = s;
    }
    
    @Override
    public void run() {
        System.out.println("Client beeing served");
        try {
            PrintWriter o = new PrintWriter(s.getOutputStream());
            BufferedReader i = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String l = i.readLine();
            while(l!=null){
                System.out.println("Server Listening");
                System.out.println("Client says: "+l);
                o.println("server says: "+l);
                o.flush();
                l = i.readLine();
            }
            System.out.println("client just leaved");
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
