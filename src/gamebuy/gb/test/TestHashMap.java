/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.test;

import gamebuy.gb.domain.GameBuyException;
import gamebuy.gb.domain.Product;
import gamebuy.gb.model.ProductService;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TestHashMap {

    public static void main(String[] args) {
        try {
            int[] idArray = {1, 3, 5, 1};

            ProductService service = new ProductService();
//        Product p = service.get(id[0]);
            Map<Product, Integer> cart = new HashMap<>();
            for (int id : idArray) {
                Product p = service.get(id);
                if (p != null) {
                    Integer quantity = cart.get(p);
                    if (quantity != null) {
                        cart.put(p, 1);
                    }else{
                        cart.put(p, 1+quantity);
                    }
                }
            }
            int sum = 0;
            double totalAmount = 0;
            
            for(Product p :cart.keySet()){
                System.out.println(p.getId()+","+p.getName()+",購買數量為"+cart.get(p));
                sum += cart.get(p);
                totalAmount = cart.get(p)*p.getPreferentialPrice();
            }
            System.out.println("共有" + cart.size() + "種商品, 總數量: " + sum + "個 ,總金額 : " + totalAmount);
        } catch (GameBuyException ex) {
            Logger.getLogger(TestHashMap.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
