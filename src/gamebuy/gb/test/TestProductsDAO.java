/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import gamebuy.gb.domain.GameBuyException;
import gamebuy.gb.domain.GameType;
import gamebuy.gb.domain.PlatForm;
import gamebuy.gb.domain.Product;
import gamebuy.gb.model.RDBProductsDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Robbie wu
 */
public class TestProductsDAO {

    public static void main(String[] args) {
        try {
            RDBProductsDAO dao = new RDBProductsDAO();
//            Product p = new Product(0, "《刺客教條：梟雄》追加內容：最後的大君", 165.00);
//            p.setStock(10);
//            p.setUrl("http://p2.bahamut.com.tw/B/2KU/60/0001339560.JPG");
//            p.setDescription("※ 請注意：本商品需配合 PC 平台之《刺客教條：梟雄》主程式執行。\n"
//                    + "　　　　　　本商品僅適用於 Uplay 平台，不相容其他平台。\n"
//                    + "\n"
//                    + "．「《刺客教條：梟雄》追加內容：最後的大君 下載序號」一組。");
//            p.setBonus(10);
//            p.setStatus(1);
//            p.setPreferentialPrice(165);
//            p.setIssueDate(new java.util.Date(2016-06-01));
//            p.setPlatForm(PlatForm.PC);
//            p.setGameType(GameType.TRADITIONAL_CHINESE_SOFTWARE);
//            dao.insert(p);
            
//             List<Product> p = new ArrayList<>();
            Product p = new Product();
            p = dao.get(2);
            
            if (p != null) {
                System.out.println("查詢成功: " + p);
            } else {
                System.out.println("查無此商品!");
            }
//            dao.delete(p);
//            System.out.println((PlatForm.PC).getName());
        } catch (GameBuyException ex) {
            Logger.getLogger(TestProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
