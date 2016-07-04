/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class ShoppingCart {

    private Map<Product, Integer> cart = new HashMap<>();
    private Customer user;

    /**
     * @return the user
     */
    public Customer getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Customer user) {
        this.user = user;
    }

    public void add(Product p) {
        add(p, 1);
    }

    public void add(Product p, int q) {
        if (q <= 0) {
            throw new IllegalArgumentException("數量必須大於0");
        }else{
            Integer oldQ = cart.get(p);
            if(oldQ == null){
                cart.put(p, q);
            }else{
                cart.put(p, oldQ + q);
            }
        }
    }
    
    public void update(Product p,int q) {
        if (q <= 0){
            throw new IllegalArgumentException("數量必須大於零");
        }else{
            cart.put(p, q);
        }
    }
    
    public void remove(Product p,int q) {
        cart.remove(p);
        
    }

    public int size() {
        return cart.size();
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public Set<Product> keySet() {
        return cart.keySet();
    }
    
    public int getQuantity(Product p){
        Integer q = cart.get(p);
        return (q == null?0:q);
    }
    
    public int getTotalQuantity(){
        int sum = 0;
        for(Product p:cart.keySet()){
            Integer q = cart.get(p);
            sum += (q == null?0:q);
        }
        return sum;
    }
    
    public double getTotalAmount(){
        double totalAmount = 0;
        for(Product p:cart.keySet()){
            Integer q = cart.get(p);
            totalAmount += p.getPreferentialPrice()*(q == null ? 0 : q);
            
        }
        return totalAmount;
    }

    public void remove(Product p) {
       cart.remove(p);
    }
}
