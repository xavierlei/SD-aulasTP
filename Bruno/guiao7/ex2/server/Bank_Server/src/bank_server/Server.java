/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank_server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author bruno
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Bank bank = new Bank();
        
        try (ServerSocket ss = new ServerSocket(50000)) {
            while (true) {
                new ClientHandler(ss.accept(), bank).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
