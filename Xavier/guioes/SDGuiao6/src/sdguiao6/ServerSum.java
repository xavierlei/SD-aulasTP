/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author xavier
 */
public class ServerSum {
    public static void main(String [] args) throws IOException{
        ServerSocket ss = new ServerSocket(12345);
        int sum = 0;
        int mean = 0;
        int cont=0;
        Socket s = ss.accept();
        System.out.println("client accepted");
        BufferedReader i = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter o = new PrintWriter(s.getOutputStream());
        o.flush();
        String l = i.readLine();
        while(l!=null){
            cont++;
            Integer temp = new Integer(l);
            sum+=temp;
            o.println("soma: "+sum);
            o.flush();
            l = i.readLine();
            if(l==null)
                System.out.println("é nulo");
        }
        mean = sum/cont;
        o.println("média: "+mean);
        o.flush();
        System.out.println("THE END...");
    }
}
