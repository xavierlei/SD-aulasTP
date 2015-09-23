/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiao3;

/**
 *
 * @author xavier
 */
public class InvalidAccount extends Exception {

    /**
     * Creates a new instance of <code>InvalidAccount</code> without detail
     * message.
     */
    public InvalidAccount() {
    }

    /**
     * Constructs an instance of <code>InvalidAccount</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public InvalidAccount(String msg) {
        super(msg);
    }
}
