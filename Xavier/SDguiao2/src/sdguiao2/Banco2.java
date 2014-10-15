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
    private Conta [] conta;
    public Banco2(){
        int i;
        this.conta = new Conta[100];
        for(i = 0; i < 100; i++){
            conta[i] = new Conta();
        }
    }
    public void credito(int nConta, long valor){
            conta[nConta].deposito(valor);
        
    }
    public boolean debito(int nConta, long valor){
        synchronized(this){
            if(conta[nConta].saldo() < valor)
                return false;
            conta[nConta].levantamento(valor);
            return true;
        }
    }
    public long saldo(int nConta){
            return conta[nConta].saldo();
    }
    public boolean transferir(int origem, int destino, long valor){
            if(!debito(destino,valor))
                return false;
            credito(origem,valor);
            return true;
    }
    public void print(){
        int i;
        for(i=0; i < 100; i++)
            System.out.println(this.conta[i].saldo());
    }
}
