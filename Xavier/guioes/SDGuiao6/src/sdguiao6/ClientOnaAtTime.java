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
import java.util.Scanner;

/**
 *
 * @author xavier
 */
public class ClientOnaAtTime {
    
    public static void main(String[] args)throws IOException{
        Socket s = new Socket("localhost",12345);

        
            PrintWriter o = new PrintWriter(s.getOutputStream());
            BufferedReader i = new BufferedReader(new InputStreamReader(s.getInputStream()));
        while(true){
            Scanner in = new Scanner(System.in);
            String st = in.nextLine();
            o.println(st);
            o.flush();
            String l = i.readLine();
            System.out.println("o servidor diz: "+l);   
        }
    }
}
