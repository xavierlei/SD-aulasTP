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
        this.conta = new Conta[10];
        for(i = 0; i < 10; i++){
            conta[i] = new Conta();
        }
    }
    public void credito(int nConta, long valor){
            conta[nConta].deposito(valor);
        
    }
    public boolean debito(int nConta, long valor){
         return conta[nConta].levantamento(valor);
    }
    public long saldo(int nConta){
            return conta[nConta].saldo();
    }
    public boolean transferir(int origem, int destino, long valor){
        //synchronized é recursivo, o mesmo thread n é impedido de
        //entrar na mesma secção crítica
        //isto vai entrar em lock na situação em que ha duas threads com
        //operações simetricas
        synchronized(conta[destino]){
            synchronized(conta[origem]){
                 if(!debito(destino,valor))
                return false;
            credito(origem,valor);
            return true;
            }
        }         
    }
    public void print(){
        int i;
        for(i=0; i < 10; i++)
            System.out.println(this.conta[i].saldo());
    }
}
