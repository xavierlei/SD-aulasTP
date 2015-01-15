/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdguiao5;

/**
 *
 * @author xavier
 */
public class SDguiao5 {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        int i;
        Warehouse wh = new Warehouse();
        Fornecedor [] f;
        f = new Fornecedor[2];
        Cliente [] c;
        c = new Cliente[20];
        for(i = 0; i < 2; i++){
            f[i] = new Fornecedor(wh);
            f[i].start();
        }
        for(i = 0; i < 10; i++){
            c[i] = new Cliente(wh);
            c[i].start();
        }
        
        for(i = 0; i < 2; i++){
            f[i] = new Fornecedor(wh);
            f[i].join();
        }
        for(i = 0; i < 10; i++){
            c[i] = new Cliente(wh);
            c[i].join();
        }
        
    }
    
}
