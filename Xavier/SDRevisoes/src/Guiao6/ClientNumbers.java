/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao6;

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
public class ClientNumbers {
    public static void main(String [] args) throws IOException{
        Socket s = new Socket("localhost",1234);
        Scanner in = new Scanner(System.in);
        BufferedReader i = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter o = new PrintWriter(s.getOutputStream());
        String l = "0";
        while(!l.equals("exit")){
            l = in.nextLine();
            if(!l.equals("exit")){
                o.println(l);
                o.flush();
            }
        }
        s.shutdownOutput();
        l = i.readLine();
        System.out.println("Total: "+l);
    }
}
