package sdguiao5;

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
public class Cliente extends Thread {
     private Warehouse wh;
     public Cliente(Warehouse w){
        this.wh = w;
     }
     @Override
     public void run(){
         String [] s = {"MacBook Pro","Disco Externo","penDrive","caneta","pen drive","laptop"};
         try {
             wh.consume(s);
         } catch (InterruptedException ex) {
             System.out.println(ex.toString());
         }
     }
}
