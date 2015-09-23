/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pergunta3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xavier
 */
public class ClientReader extends Thread{
    
    private Socket s;
    public ClientReader(Socket s){
        this.s = s;
    }
    
    @Override
    public void run(){
        BufferedReader i = null;
        try {
            i = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String l = i.readLine();
            while(l!=null){
                System.out.println(l);
                l = i.readLine();
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(ClientReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                i.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
