/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author bruno
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BankStub stub;
        
        try (Socket s = new Socket("localhost", 50000)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(s.getOutputStream());
            
            stub = new BankStub(s, in, out);
            
            while (true) {
                String input = stdin.readLine();
                if (input == null) {
                    break;
                }
                String ret = stub.parseAndSend(input);
                System.out.println(ret);
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
