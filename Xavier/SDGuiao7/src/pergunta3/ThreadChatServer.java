/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pergunta3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xavier
 */
public class ThreadChatServer extends Thread {
    
    private Socket s;
    private ArrayList<Socket> list;
    public ThreadChatServer(Socket s, ArrayList<Socket> list){
        this.s = s;
        this.list = list;
    }
    
    public void run(){
        PrintWriter o = null;
        try { 
            BufferedReader i = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String l = i.readLine();
            while(l!=null){
                for(Socket sock : list){
                    o = new PrintWriter(sock.getOutputStream());
                    if(!s.equals(sock)){
                        o.println(l);
                        o.flush();
                    }
                }
                l = i.readLine();
            }
            s.close();
        } 
        catch (IOException ex) {    
        }
    }
    
}
