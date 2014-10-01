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
public class ClienteThread2 extends Thread{
    public Banco2 banco;
    public int numConta;
    public ClienteThread2(Banco2 banco, int numC){
        this.numConta = numC;
        this.banco = banco;
    }
    @Override
    public void run(){
        int n = (int) ((Math.random()*System.currentTimeMillis())%100);
        long v = (long) ((Math.random()*System.currentTimeMillis())%876);
        banco.credito(numConta, 1000);
        banco.transferir(numConta, n, v);
        System.out.println(banco.saldo(numConta));
        
    }
}
