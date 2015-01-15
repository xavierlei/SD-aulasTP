package sdguiao6;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xavier
 */
public class Server {
    public static void main(String[] args) throws IOException{
        ServerSocket ss = new ServerSocket(12345);
        System.out.println("Socket Criado");
        Socket s = ss.accept();
        System.out.println("Cliente aceite");
        
        PrintWriter o = new PrintWriter(s.getOutputStream());
        BufferedReader i = new BufferedReader(new InputStreamReader(s.getInputStream()));
        o.println("ola!");
        o.flush();
        
        String l = i.readLine();
        System.out.println("o cliente enviou: "+l);
        s.close();
        ss.close();
    }
}
