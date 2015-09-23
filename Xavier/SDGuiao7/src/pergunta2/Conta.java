/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pergunta2;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author xavier
 */
public class Conta {
    private float saldo;
    public ReentrantLock l = new ReentrantLock();
    
    public Conta(){
        this.saldo = 0;
    }
    public Conta(Conta c){
        this.saldo = c.saldo();
    }
 
    public void deposito(float valor){
            saldo = saldo + valor;
    }
    public boolean levantamento(float valor){
            if(saldo >= valor){
                saldo = saldo - valor;
                return true;
            }
            else return false;
    }
    public float saldo(){
            return this.saldo;
    }
}
