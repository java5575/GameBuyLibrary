/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.model;

import gamebuy.gb.domain.Customer;
import gamebuy.gb.domain.GameBuyException;
import gamebuy.gb.domain.Order;
import gamebuy.gb.domain.ShoppingCart;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class OrderService {
    private RDBOrderDAO dao = new RDBOrderDAO();
    
    public Order makeOrder(Customer user,ShoppingCart cart) throws GameBuyException{
        Order order = new Order();
        order.setCustomer(user);
        order.add(cart);
        order.setCreatedTime(new java.util.Date());
        return order;
    }

    public void create(Order order) throws GameBuyException {
        dao.create(order);
    }

//    public void update(Order c) throws GameBuyException {
//        dao.update(c);
//    }
//
//    public void delete(Order c) throws GameBuyException {
//        dao.delete(c);
//    }

    public Order get(Integer id) throws GameBuyException {
        return dao.get(id);
    }

    public List<Order> getByCustomer(String customerEmail) throws GameBuyException {
        return dao.getByCustomer(customerEmail);
    }
    
    
    
}
