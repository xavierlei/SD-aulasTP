
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pedro
 */
public class Soma_server {

    /**
     * @param args the command line arguments
     */
    
    static class Sum
    {
        int soma=0;
        
        synchronized void sum(int i){soma+=i;}
        synchronized int getSoma(){return soma;}
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
     
        final Sum soma = new Sum();
       
        
      try{
              ServerSocket ss = new ServerSocket(50000);
              while(true){     
                   try {
                       Socket cs = ss.accept();       
                       new ClientHandler(cs,soma).start();
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
