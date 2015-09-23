/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiao2;

/**
 *
 * @author xavier
 */
public class Conta2 {
    private long saldo;
    private Integer numC;
    public Conta2(long value, int num){
        this.saldo = value;
        this.numC = num;
    }
    public synchronized long saldo(){
        return this.saldo;
    }
    public synchronized void deposit(long value){
        this.saldo += value;
    }
    public synchronized void levant(long v){
        this.saldo -= v;
    }
}
