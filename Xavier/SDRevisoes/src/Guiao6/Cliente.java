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
public class Cliente {
    public static void main(String [] args) throws IOException{
        Socket s = new Socket("localhost",1234);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream());
        Scanner input = new Scanner(System.in);
        String l="";
        String li = "";
        while(li != null && !l.equals("exit")){
            l = input.nextLine();
            out.println(l);
            out.flush();
            li = in.readLine();
            System.out.println(li);
        }
    }
}
