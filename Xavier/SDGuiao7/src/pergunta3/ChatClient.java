/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pergunta3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author xavier
 */
public class ChatClient {
    public static void main(String [] args) throws IOException{
        Socket s = new Socket("localhost", 12345);
        PrintWriter o = new PrintWriter(s.getOutputStream());
        ClientReader c = new ClientReader(s);
        ClientWritter w = new ClientWritter(s);
        c.start();
        w.start();
    }
}
