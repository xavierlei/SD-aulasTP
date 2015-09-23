/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiao2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author xavier
 */

//Implemente uma classe Banco que oferec ̧a os me ́todos de consulta, cre ́dito e de ́bito de valores sobre 
//um nu ́mero 
//fixo de contas (com saldo inicial nulo). Utilize exclusa ̃o mu ́tua ao n ́ıvel do objecto Banco.
public class Banco {
    private Conta [] contas;
    public Banco(){
        contas  = new Conta[20];
    }
    public synchronized long getSaldo(int num){
        return contas[num].saldo();
    }
    public synchronized void credito(int num, long val){
        contas[num].deposit(val);
    }
    public synchronized boolean debito(int num, long val){
        if(contas[num].saldo() < val)
            return false;
        contas[num].levant(val);
        return true;
    }
    public synchronized boolean transferir(int ori, int dest, long val){
        boolean b = debito(ori,val);
        if(!b)
            return false;
        credito(dest,val);
        return true;
    }
}
