/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BancoServer.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author Pedro
 */
public class BancoStub implements BancoServer.BancoInt{
    
    final Socket sc;
    final BufferedReader br;
    final PrintWriter pw;
    
    public BancoStub(String local,int port) throws IOException
    {
          sc  = new Socket("localhost", 50000);
          br =  new BufferedReader(new InputStreamReader(sc.getInputStream()));
          pw =new PrintWriter(sc.getOutputStream());
    }

    @Override
    public int createAccount(float initialbalance) {
        
        String m = "crtAcc:"+initialbalance;
        pw.println(m);
        pw.flush();
        
        try {
            String s = br.readLine();
            int res = Integer.parseInt(s);
            return res;
        } catch (IOException ex) {
            //Logger.getLogger(BancoStub.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
       
    }

    @Override
    public float closeAccount(int id) throws InvalidAccount {
       String m = "closeAcc:"+id;
        pw.println(m);
        pw.flush();
        float res;
        try {
           String s = br.readLine();
            res = Float.parseFloat(s);
             return res;
        } catch (IOException ex) {
            throw new InvalidAccount();
        }
        
       
    }

    @Override
    public void transfer(int from, int to, float amount) throws InvalidAccount, NotEnoughFunds {
       String m = "transfer:"+from+","+to+","+amount;
        pw.println(m);
        pw.flush();
        try {
           String s = br.readLine();
           if(s.equals("invalidaccount")) throw new InvalidAccount();
           if(s.equals("nofunds")) throw new NotEnoughFunds();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public float totalBalance(int[] accts) throws InvalidAccount {
      String m = "totalBal:";
      for(int i =0 ; i < accts.length; i++)
      {
          if(i== accts.length-1)
                m +=accts[i];
          else
          {
               m+=accts[i]+",";     
          }
              
      }
      pw.println(m);
      pw.flush();
           
        try {
           String s = br.readLine();
           float res = Float.parseFloat(s);
           return res;
        } catch (Exception ex) {
           return -1;
        }
           
           
    }
    
}
