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
public class ClienteThread extends Thread {
    public Banco banco;
    public int numConta;
    public ClienteThread(Banco banco, int numC){
        this.numConta = numC;
        this.banco = banco;
    }
    public void run(){
        int n = (int) ((Math.random()*System.currentTimeMillis())%100);
        long v = (long) ((Math.random()*System.currentTimeMillis())%876);
        banco.credito(numConta, 1000);
        banco.transferir(numConta, n, v);
        System.out.println(banco.saldo(numConta));
        
    }
}
