/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author xavier
 */
public class Client {
    public static void main(String[] args) throws IOException{
        Socket s = new Socket("localhost",12345);
        
        
        PrintWriter o = new PrintWriter(s.getOutputStream());
        BufferedReader i = new BufferedReader(new InputStreamReader(s.getInputStream()));
        
        String l = i.readLine();
        System.out.println("o servidor diz: "+l);
        o.println("bom dia!");
        o.flush();
        
    }
}
