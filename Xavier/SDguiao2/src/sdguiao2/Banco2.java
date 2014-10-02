package sdguiao2;

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
    private long [] conta;
    public Banco2(){
        int i;
        this.conta = new long[100];
        for(i=0; i<100; i++)
            this.conta[i] = 0;
    }
    public void credito(int nConta, long valor){
        synchronized(this){
            conta[nConta] = conta[nConta] + valor;
        }
        
    }
    public boolean debito(int nConta, long valor){
        synchronized(this){
            if(conta[nConta] < valor)
                return false;
            conta[nConta] = conta[nConta] - valor;
            return true;
        }
    }
    public long saldo(int nConta){
        synchronized(this){
            return this.conta[nConta];
        }
    }
    public boolean transferir(int origem, int destino, long valor){
        //nota: o synchronized é recursivo, ié entrando num synchronized ele
        //pode chamar outro método synchronized
        synchronized(this){
            if(!debito(destino,valor))
                return false;
            credito(origem,valor);
            return true;
        }   
    }
}
