/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.domain;

/**
 *
 * @author Robbie wu
 */
public class GameBuyException extends Exception {

    public GameBuyException() {
    }

    public GameBuyException(String message) {
        super(message);
    }

    public GameBuyException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameBuyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    

}
