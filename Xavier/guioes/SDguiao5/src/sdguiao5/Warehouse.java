package sdguiao5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xavier
 */
public class Warehouse {
    private Map<String,InfoProduto> stock;
    private ReentrantLock l;
    
    public Warehouse(){
        stock = new HashMap<String,InfoProduto>();
        l = new ReentrantLock();
        for(int i = 0; i < 20; i++){
            String s = "Prod"+i;
            InfoProduto ip = new InfoProduto(i,s,l);
        }
    }
    
    
    public void supply(String item, int quantity){
        InfoProduto ip;
        l.lock();
        try {
            if(stock.containsKey(item)){
                stock.get(item).supply(quantity);
                System.out.println(item+" suplied");
            }
            else{
                ip = new InfoProduto(quantity,item,l);
                stock.put(item, ip);
            }
            
            stock.get(item).c.signalAll();
        } finally {
            l.unlock();
        }
    }
    
    public void consume(String[] items) throws InterruptedException{
        l.lock();
        try{
            
            for(String s : items){
                if(!stock.containsKey(s))
                    stock.put(s, new InfoProduto(10,s,l));
            } 
            
            
            for(int i =0; i < items.length; i++ ){
                if(stock.get(items[i]).getQuant() == 0){
                    stock.get(items[i]).c.await();
                    i = 0;
                }
            }
                
                
            for(String s : items){
                stock.get(s).consume();
                System.out.println("Consumed: "+s);
            }
            
        }
        finally{
            l.unlock();
        }
    }
}

/*
*Parece-me que esta solução tem o problema de starvation
*/