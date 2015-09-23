/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author xavier
 */
public class InfoProduto {
    private int quantidade;
    private String designacao;
    public Condition c;
    
    public InfoProduto(){
        quantidade = 0;
        designacao = "Produto Genérico";
    }
    
    public InfoProduto(int q, String name, ReentrantLock l){
        quantidade = q;
        designacao = name;
        c = l.newCondition();
    }
    
    public void supply(int quant){
        this.quantidade += quant;
    }
    public void consume(){this.quantidade --;}
    public int getQuant(){return this.quantidade;}
}
