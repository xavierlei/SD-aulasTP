/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao1;

/**
 *
 * @author xavier
 */
public class Tarefa implements Runnable {
    private int indice;
    
    public Tarefa(int i){
        this.indice = i;
    }
    @Override
    public void run() {
        this.tarefa();
    }
    
    public void tarefa(){
        int i;
        for(i = 0; i <= this.indice; i++){
            System.out.print(i);
        }
        System.out.print("\n");
    }
}
