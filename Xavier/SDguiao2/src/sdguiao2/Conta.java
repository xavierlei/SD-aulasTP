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
    public Conta(){this.saldo = 0;}
    
    synchronized void deposito(long valor){
        this.saldo+=valor;
    }
    
    synchronized void levantamento(long val){
        this.saldo -=val;
    }
    synchronized long saldo(){
        return this.saldo;
    }
}
