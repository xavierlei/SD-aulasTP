/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pergunta3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xavier
 */
public class ClientWritter extends Thread {
    
    private Socket s;
    
    public ClientWritter(Socket s){
        this.s = s;
        
    }
    public void run(){
        PrintWriter o = null;
        try {
            o = new PrintWriter(s.getOutputStream());
            Scanner in = new Scanner(System.in);
            System.out.print(">");
            String st = in.nextLine();
            while(!st.equals("exit")){
                o.println(st);
                o.flush();
                st = in.nextLine();
            }
            s.close();
            
            
        } catch (IOException ex) {
            Logger.getLogger(ClientWritter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            o.close();
        }
    }
}
