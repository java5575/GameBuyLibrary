/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.test;

import gamebuy.gb.domain.Customer;
import gamebuy.gb.domain.GameBuyException;
import gamebuy.gb.domain.Order;
import gamebuy.gb.domain.PaymentType;
import gamebuy.gb.domain.Product;
import gamebuy.gb.domain.ShippingType;
import gamebuy.gb.domain.ShoppingCart;
import gamebuy.gb.model.CustomerService;
import gamebuy.gb.model.OrderService;
import gamebuy.gb.model.ProductService;
import gamebuy.gb.model.RDBOrderDAO;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TestOrderServiceMakeOrder {
    public static void main(String[] args) {
        try {
            int[] idArray = {1, 3, 5, 1};

            ProductService service = new ProductService();

            ShoppingCart cart = new ShoppingCart();

            for (int id : idArray) {
                Product p = service.get(id);
                if (p != null) {
                    cart.add(p);
                }
            }
                CustomerService cservice = new CustomerService();
                Customer c1 = cservice.login("dawei.wang@gamil.com", "b22126");

                cart.setUser(c1);
                System.out.println(cart);
                
                OrderService orderService = new OrderService();

                Order order = orderService.makeOrder(c1, cart);
                order.setCreatedTime(new Date());
//                order.add(cart);
                order.setPaymentType(PaymentType.HOME);
                order.setPaymentFee(PaymentType.HOME.getFee());
                
                order.setShippingType(ShippingType.HOME);
                order.setShippingFee(ShippingType.HOME.getFee());
                order.setReceiverName("李四方");
                order.setReceiverEmail("squarl_lee@gmail.com");
                order.setReceiverPhone("0225149191");
                order.setShippingAddress("台北市復興北路99號14樓");
                order.setStatus(0);
//                order.setBonus(0);

                System.out.println("order:" + order);
                
                orderService.create(order);
                System.out.println("下單成功!" + order.getId() );

//                RDBOrderDAO dao = new RDBOrderDAO();
//                System.out.println(dao.getByCustomer("dawei.wang@gamil.com"));
            
            System.out.println("共有" + cart.size() + "種商品, 總數量: " + cart.size() + "個 ,總金額 : " + cart.getTotalAmount());
        } catch (GameBuyException ex) {
            Logger.getLogger(TestHashMap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }

