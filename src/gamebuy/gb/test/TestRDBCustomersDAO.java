/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.test;

import gamebuy.gb.domain.Customer;
import gamebuy.gb.domain.GameBuyException;
import gamebuy.gb.model.RDBCustomersDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TestRDBCustomersDAO {

    public static void main(String[] args) {
        try {
            RDBCustomersDAO dao = new RDBCustomersDAO();
            Customer customer = dao.get("dawei.wang@gamil.com");
        
            //測試 get 方法
            if (customer == null) {
                System.err.println("查無此客戶!");
            } else {
                System.err.println(customer);
            }

            //測試delete方法
//            if (customer != null) {
//                System.out.println(customer);
//                dao.delete(customer);
//            } else {
//                System.out.println("查無此客戶");
//            }

            //測試update方法
//            if (customer != null) {
//                System.out.println(customer);
//                customer.setName("王強壯");
//                dao.update(customer);
//            } else {
//                System.out.println("查無此客戶");
//            }

            //測試 insert 方法，請先建立一個新的客戶
//            Customer customer1 = new Customer();
//            customer1.setId("A126161787");
//            customer1.setEmail("dawei.wang@gamil.com");
//            customer1.setPassword("b22126");
//            customer1.setAddress("台北市復興北路99號16F");
//            customer1.setPhone("0987666066");
//            customer1.setName("王大偉");
//            customer1.setBirthday("1966-6-6");
//            customer1.setStatus(0);
//            customer1.setBonus(0);
            // dao.insert(customer1);
            //測試 getAll 方法
            //  System.out.println(dao.getAll());
        } catch (GameBuyException ex) {
            Logger.getLogger(TestRDBCustomersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
