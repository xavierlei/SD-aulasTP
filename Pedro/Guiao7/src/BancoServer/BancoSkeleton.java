/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BancoServer;

/**
 *
 * @author Pedro
 */
public class BancoSkeleton {
    Banco b;
    public BancoSkeleton(Banco b){this.b =b;}
    
    public String parseMessage(String m) throws BancoInt.InvalidAccount
    {
        String[] call = m.split(":");
        if(call.length < 1) return null;
        switch(call[0])
        {
            case "crtAcc":{
                if(call.length < 2) return null;
                float f = Float.parseFloat(call[1]);
                //
                int res = b.createAccount(f);
                //
                return res+"";
            }
            
            case "closeAcc":{
                if(call.length < 2) return null;
                int f = Integer.parseInt(call[1]);
                float res = b.closeAccount(f);       
                return res+"";
            }
        }
        return null;
    }
}
