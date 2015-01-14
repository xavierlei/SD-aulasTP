package pergunta3;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xavier
 */
public class ChatServer {
    public static void main(String [] args) throws IOException{
        ServerSocket ss = new ServerSocket(12345);
        ArrayList<Socket> list = new ArrayList<Socket>();
        while(true){
            System.out.println("waiting for client");
            Socket s = ss.accept();
            list.add(s);
            ThreadChatServer t = new ThreadChatServer(s,list);
            t.start();
        }
    }
}
