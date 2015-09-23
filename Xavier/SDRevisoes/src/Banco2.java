
import guiao2.Conta;
import guiao2.Conta2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xavier
 */
public class Banco2 {
    private Conta2 [] contas;
    public Banco2(){
        contas  = new Conta2[20];
    }
    public long getSaldo(int num){
        return contas[num].saldo();
    }
    public void credito(int num, long val){
        contas[num].deposit(val);
    }
    public boolean debito(int num, long val){
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
