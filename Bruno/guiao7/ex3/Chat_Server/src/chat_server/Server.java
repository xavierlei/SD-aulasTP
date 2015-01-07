/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author bruno
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        ArrayList<Socket> list = new ArrayList<>();
        
        try (ServerSocket ss = new ServerSocket(50000)) {
            while(true) {
                Socket socket = ss.accept();
                list.add(socket);
                new ClientHandler(socket, list).start();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }  
}
