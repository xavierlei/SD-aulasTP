package pergunta1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xavier
 */
public class ClientSum {
    public static void main(String [] args) throws IOException{
        String l;
        Socket s = new Socket("localhost",12345);
        PrintWriter o = new PrintWriter(s.getOutputStream());
        BufferedReader i = new BufferedReader(new InputStreamReader(s.getInputStream()));
        Scanner in = new Scanner(System.in);
        System.out.print(">");
        String st = in.nextLine();
        while(!st.equals("exit")){
            o.println(st);
            o.flush();
            l = i.readLine();
            System.out.println("server says: "+l);
            System.out.print(">");
            st = in.nextLine();
        }
        s.shutdownOutput();
        l = i.readLine();
        System.out.println("server says: "+l);
    }
}
