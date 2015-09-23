/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao1;

/**
 *
 * @author xavier
 */
public class Counter {
    public int c;
    public Counter(){
        this.c = 0;
    }
    public synchronized void increment(){
        c = c+1;
    }
}
