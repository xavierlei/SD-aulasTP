/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao7Perg1;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author xavier
 */
public class Contador {
    public int soma;
    public ReentrantLock l;
    public ArrayList<PrintWriter> ps;
    
    public Contador(){
        soma = 0;
        l = new ReentrantLock();
        ps = new ArrayList<PrintWriter>();
    }
    
}
