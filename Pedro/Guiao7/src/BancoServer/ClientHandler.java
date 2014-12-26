package BancoServer;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


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
    Banco b;
    Socket sc;
    BancoSkeleton skeleton;

    public ClientHandler(Socket sc,Banco b)
    {
        this.sc = sc;
        this.b = b;
        this.skeleton = new BancoSkeleton(b);
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
                   

                   /** if(s.equals("sair") || s == null) {
                        interactions--;
                        double media = ((float)LocalSum/(float)interactions);
                        pw.println("media :"+media);
                        pw.flush();
                        pw.close();
                        break;
                    }**/
                    try{
                        String res = skeleton.parseMessage(s);
                        pw.println(res);
                        pw.flush();

                    } catch (BancoInt.InvalidAccount ex) {
                         pw.println("invalidaccount");
                        pw.flush();
                }

           }
       }catch(IOException ex){ex.printStackTrace();}
    } 
}
