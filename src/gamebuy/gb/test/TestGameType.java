/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.test;

import gamebuy.gb.domain.GameBuyException;
import gamebuy.gb.domain.GameType;
import gamebuy.gb.domain.Product;

/**
 *
 * @author Administrator
 */
public class TestGameType {
    public static void main(String[] args) {
        String type2 = GameType.SOFTWARE.getChName();
        GameType type1 = GameType.SOFTWARE;
        Product p1 = new Product();
        Product p2 = new Product();
//        p1.setGameType(type1);
//        
//        System.out.println(p1.getGameType().getName());
//      System.out.println(type);  Product p = new Product();
//        p.setGameType(type);
        
    }
    
}
