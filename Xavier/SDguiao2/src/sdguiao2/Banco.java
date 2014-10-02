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
public class Banco {
    private long [] conta;
    public Banco(){
        int i;
        this.conta = new long[100];
        for(i=0; i<100; i++)
            this.conta[i] = 0;
    }
    public synchronized void credito(int nConta, long valor){
        conta[nConta] = conta[nConta] + valor;
        
    }
    public synchronized boolean debito(int nConta, long valor){
        if(conta[nConta] < valor)
            return false;
        conta[nConta] = conta[nConta] - valor;
        return true;
    }
    public synchronized long saldo(int nConta){
        return this.conta[nConta];
    }
    public synchronized boolean transferir(int origem, int destino, long valor){
        //nota: o synchronized é recursivo, ié entrando num synchronized ele
        //pode chamar outro método synchronized
        if(!debito(destino,valor))
            return false;
        credito(origem,valor);
        return true;
        
        
    }
}
