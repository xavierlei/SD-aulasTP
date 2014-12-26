
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pedro
 */
 public class ClientHandler extends Thread
{
    int interactions = 0;
    int LocalSum = 0;
    Soma_server.Sum soma;
    Socket sc;

    public ClientHandler(Socket sc,Soma_server.Sum soma)
    {
        this.sc = sc;
        this.soma = soma;
    }

    @Override
    public void run()
    {
        try{
            while(true){
                    InputStreamReader ir = new InputStreamReader(sc.getInputStream());
                    BufferedReader br = new BufferedReader(ir);
                    PrintWriter pw = new PrintWriter(sc.getOutputStream());
                    String s = br.readLine();
                    interactions++;

                    if(s.equals("sair") || s == null) {
                        interactions--;
                        double media = ((float)LocalSum/(float)interactions);
                        pw.println("media :"+media);
                        pw.flush();
                        pw.close();
                        break;
                    }
                    try{
                        int i = Integer.parseInt(s);
                        soma.sum(i);
                        LocalSum+=i;
                        pw.println(soma.getSoma());
                        pw.flush();

                    }catch(NumberFormatException ex){pw.println("not a number");interactions--;pw.flush();}

           }
       }catch(IOException ex){ex.printStackTrace();}
    } 
}
