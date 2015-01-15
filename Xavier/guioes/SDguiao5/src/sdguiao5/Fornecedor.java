/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao5;

/**
 *
 * @author xavier
 */
public class Fornecedor extends Thread {
    
    private Warehouse wh;
    
    public Fornecedor(Warehouse w){
        this.wh = w;
    }
    
    @Override
    public void run(){
        String [] s = {"MacBook Pro","Disco Externo","penDrive"};
        wh.supply("MacBook Pro", 10);
        wh.supply("penDrive", 2);
    }
    
}
