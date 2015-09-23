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
public class Conta {
    private long saldo;
    private Integer numC;
    public Conta(long value, int num){
        this.saldo = value;
        this.numC = num;
    }
    public long saldo(){
        return this.saldo;
    }
    public void deposit(long value){
        this.saldo += value;
    }
    public void levant(long v){
        this.saldo -= v;
    }
}
