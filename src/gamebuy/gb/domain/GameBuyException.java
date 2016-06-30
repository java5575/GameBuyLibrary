/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.domain;

/**
 *
 * @author Administrator
 */
public class GameBuyException extends Exception {

    /**
     * Creates a new instance of <code>GameBuyException</code> without detail
     * message.
     */
    public GameBuyException() {
    }

    /**
     * Constructs an instance of <code>GameBuyException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public GameBuyException(String msg) {
        super(msg);
    }

    public GameBuyException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
