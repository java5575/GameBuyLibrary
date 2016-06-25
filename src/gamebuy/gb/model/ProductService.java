/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.model;

import java.util.List;
import gamebuy.gb.domain.GameBuyException;
import gamebuy.gb.domain.Product;

/**
 *
 * @author Robbie wu
 */
public class ProductService {
    private RDBProductsDAO dao = new RDBProductsDAO();

    public void insert(Product p) throws GameBuyException {
        dao.insert(p);
    }

    public void update(Product p) throws GameBuyException {
        dao.update(p);
    }

    public void delete(Product p) throws GameBuyException {
        dao.delete(p);
    }

    public List<Product> getAll() throws GameBuyException {
        return dao.getAll();
    }

    public Product get(int id) throws GameBuyException {
        return dao.get(id);
    }
}
