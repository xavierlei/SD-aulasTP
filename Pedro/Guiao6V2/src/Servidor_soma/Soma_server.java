/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package Servidor_soma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


/**
 *
 * @author Pedro
 */
public class Soma_server {

   static class ClientHandler extends Thread
   {
       Socket cs;
       public ClientHandler(Socket cs){
           this.cs = cs;
                   
       }
      
       @Override
       public void run()
       {
           int soma = 0; int inputs = 0;
                
           try{
                while(true){
                            
                                     InputStreamReader ir = new InputStreamReader(cs.getInputStream());
                                     BufferedReader br = new BufferedReader(ir);
                                     PrintWriter pw = new PrintWriter(cs.getOutputStream());
                                     String s = br.readLine();
                                     inputs++;
                                     if(s.equals("sair")||s == null) {
                                         inputs--;
                                         int media = soma/inputs;
                                         pw.println("media :"+media);
                                         pw.flush();
                                         break;
                                     }
                                     try{
                                        int i = Integer.parseInt(s);
                                        soma+= i; 
                                        pw.println(soma);
                                        pw.flush();
                                        
                                     }catch(NumberFormatException ex){pw.println("not a number");inputs--;pw.flush();}

                                     
                        }
                cs.close();
                
           }catch(Exception e){e.printStackTrace();
           }
         }
       }


    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
         try{
              ServerSocket ss = new ServerSocket(50000);
              while(true){     
                   try {
                       Socket cs = ss.accept();       
                      new ClientHandler(cs).start();
                      
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
