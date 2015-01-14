/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author xavier
 */
public class ServerOneAtTime {
    public static void main(String[] args) throws Exception{
        while(true){
            ServerSocket ss = new ServerSocket(12345);
            System.out.println("ServerSocket Criado");
            Socket s = ss.accept();
            System.out.println("Cliente aceite");

            PrintWriter o = new PrintWriter(s.getOutputStream());
            BufferedReader i = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String l = i.readLine();
                while(l!=null){
                    System.out.println("o cliente enviou: "+l);
                    o.println(l);
                    o.flush();
                    l = i.readLine();
                }
            s.close();
            ss.close();
        }
    }
}
//sair do telnet: shft+ctrl+´