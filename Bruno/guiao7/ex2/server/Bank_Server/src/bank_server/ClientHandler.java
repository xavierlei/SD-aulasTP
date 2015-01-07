/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author bruno
 */
public class ClientHandler extends Thread {

    private final Socket socket;
    private final BankSkeleton skeleton;

    public ClientHandler(Socket socket, Bank bank) {
        this.socket = socket;
        this.skeleton = new BankSkeleton(bank);
    }

    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) {
            while (true) {
                String str = in.readLine();
                if (str == null) 
                    break;
                
                System.out.println(str);
                String msg = skeleton.parseAndCall(str);
//                System.out.println("response: " + msg);
                out.println(msg);
                out.flush();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
