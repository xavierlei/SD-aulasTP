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
public class NotEnougthFunds extends Exception {

    /**
     * Creates a new instance of <code>NotEnougthFunds</code> without detail
     * message.
     */
    public NotEnougthFunds() {
    }

    /**
     * Constructs an instance of <code>NotEnougthFunds</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public NotEnougthFunds(String msg) {
        super(msg);
    }
}
