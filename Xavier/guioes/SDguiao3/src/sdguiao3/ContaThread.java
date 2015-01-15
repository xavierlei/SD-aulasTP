/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao3;

/**
 *
 * @author xavier
 */
public class ContaThread extends Thread {
    public Banco b;
    public int nConta;
    
    public ContaThread(Banco b, int nConta){
        this.b = b;
        this.nConta = nConta;
    }
    
    @Override
    public void run(){
        int n = (int) ((Math.random()*System.currentTimeMillis())%100);
        float v = (float) ((Math.random()*System.currentTimeMillis())%1543);
        try {
            b.transfer(nConta, n, v);
        } catch (InvalidAccount ex) {
           System.out.println(ex.getMessage());
        } catch (NotEnougthFunds ex) {
            System.out.println(ex.getMessage());
        }
        b.createAccount(2300);
        n = (int) ((Math.random()*System.currentTimeMillis())%10);
        try{
            b.closeAccount(n);
        }catch(InvalidAccount ex){
            System.out.println(ex.getMessage());
        }
    }
}
