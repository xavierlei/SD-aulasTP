/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pergunta1;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author xavier
 */
public class Contador {
    private int total;
    private int cont;
    public ReentrantLock l;
    
    public Contador(){
        total = 0;
        cont = 0;
        l = new ReentrantLock();
    }
    
    public int sum(int n){
        total += n;
        cont++;
        return total;
    }
    public int media(){
        return total/cont;
    }
    
}
