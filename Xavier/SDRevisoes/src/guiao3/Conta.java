/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiao3;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author xavier
 */
public class Conta {
    private Integer num;
    private float saldo;
    public ReentrantLock l;
    public Conta(Integer n, float value){
        num = n;
        saldo = value;
        l = new ReentrantLock();
    }
    public void deposita(float v){
        saldo += v;
    }
    public void levanta(float v){
        saldo -= v;
    }
    public float saldo(){
        return this.saldo;
    }
}
