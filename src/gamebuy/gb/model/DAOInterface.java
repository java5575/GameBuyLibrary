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
public interface DAOInterface<K, T> {

    //↓刪除客戶開始
    void delete(T c) throws GameBuyException;
    //↑刪除客戶結束

    //↓查詢一個客戶
    T get(T c) throws GameBuyException;
    //↑查詢一個客戶結束

    //↓查詢全部客戶
    List<Customer> getAll() throws GameBuyException;
    //↑查詢全部客戶結束

    //↓新增客戶開始
    void insert(T c) throws GameBuyException;
    //↑新增客戶結束

    //↓修改客戶開始
    void update(T c) throws GameBuyException;
    //↑修改客戶結束
    
}
