/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BancoServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Pedro
 */
public class BancoServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Banco b  =new Banco();
        try{
              ServerSocket ss = new ServerSocket(50000);
              while(true){     
                   try {
                       Socket cs = ss.accept();       
                       new ClientHandler(cs,b).start();
                  }catch(SocketException ex)
                  {
                      ex.printStackTrace();
                  }
            }
       
        
        }
         
        catch(IOException ex){ex.printStackTrace();
        }
    }
    
}
