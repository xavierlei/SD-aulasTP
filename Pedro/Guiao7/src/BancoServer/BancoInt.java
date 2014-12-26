/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BancoServer;


/**
 *
 * @author Pedro
 */
public interface BancoInt {
    
    static class InvalidAccount extends Exception {

        public InvalidAccount() {
         super();
         
        }
    }

    static class NotEnoughFunds extends Exception {

        public NotEnoughFunds() {
            super();
        }
    }
    
    int createAccount(float initialbalance);
    //Account get(int id) throws InvalidAccount;
    float closeAccount(int id) throws InvalidAccount;
    void transfer(int from, int to, float amount) throws InvalidAccount, NotEnoughFunds;
    public float totalBalance(int accts[]) throws InvalidAccount;

   
    
}
