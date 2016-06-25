/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.test;

import gamebuy.gb.domain.Customer;
import gamebuy.gb.domain.GameBuyException;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TestCustomer {
    public static void main(String[] args) {
        try {
            Customer customer = new Customer();
            customer.setId("A123456789");
            customer.setBirthday("2016/12/12");
            System.out.println(customer);
        } catch (GameBuyException ex) {
            Logger.getLogger(TestCustomer.class.getName()).log(Level.SEVERE, null, ex);
            if(ex.getCause()!=null){
                System.out.println("..."+ex.getCause());
            }
        }
        
    }
}
