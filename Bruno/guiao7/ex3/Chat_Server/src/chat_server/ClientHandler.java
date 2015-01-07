package chat_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author bruno
 */
public class ClientHandler extends Thread {

    private final Socket socket;
    private final ArrayList<Socket> list;

    public ClientHandler(Socket socket, ArrayList<Socket> list) {
        this.socket = socket;
        this.list = list;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) {
            while(true) {
                String str = in.readLine();
                if(str == null)
                    break;
                for(Socket s : list) {
                    out = new PrintWriter(s.getOutputStream());
                    if(!s.equals(socket)) {
                        out.println(str);
                        out.flush();
                    }
                }
            }
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
