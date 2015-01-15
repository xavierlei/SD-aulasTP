/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao2;

/**
 *
 * @author xavier
 */
public class Conta {
    private long saldo;
    public Conta(){this.saldo = 1000;}
    
    synchronized void deposito(long valor){
        this.saldo+=valor;
    }
    
    synchronized boolean levantamento(long val){
        if(this.saldo >=val){
            this.saldo -=val;
            return true;
        }
        return false;
    }
    synchronized long saldo(){
        return this.saldo;
    }
}
