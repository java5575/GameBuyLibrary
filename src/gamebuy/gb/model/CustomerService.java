/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.model;

import gamebuy.gb.domain.Customer;
import gamebuy.gb.domain.GameBuyException;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class CustomerService {

    private RDBCustomersDAO dao = new RDBCustomersDAO();

    public Customer login(String id, String pwd) throws GameBuyException {
        if (id == null || pwd == null) {
            throw new GameBuyException("帳號密碼不得為空值");
        }
        //TODO: 須完成登入(使用DAO的get)處理
        Customer c = dao.get(id);
        if (c != null && pwd.equals(c.getPassword())) {
            return c;
        }
        throw new GameBuyException("帳號或密碼不正確");
    }

    public void register(Customer c) throws GameBuyException {
        //TODO:須完成客戶註冊(insert)處理
        dao.insert(c);
    }
    //delegate method
    public void update(Customer c)throws GameBuyException{
    
        dao.update(c);
    }
//delegate method
    public void delete(Customer c) throws GameBuyException {
        dao.delete(c);
    }
//delegate method
    public List<Customer> getAll() throws GameBuyException {
        return dao.getAll();
    }
//delegate method
    public Customer get(String id) throws GameBuyException {
        return dao.get(id);
    }



}
