/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.model;

import gamebuy.gb.domain.Customer;
import gamebuy.gb.domain.GameBuyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class RDBCustomersDAO  {

    //搜尋一個客戶
    private static final String SELECT_SQL = "SELECT *  FROM customers WHERE email=?";
    //搜尋全部客戶
    private static final String SELECT_ALL_SQL = "SELECT * FROM customers";
    //新增客戶
    private static final String INSERT_SQL = "INSERT INTO customers " + "( id , email ,  password , address , phone , name , birthday ) " + "VALUES(?,?,?,?,?,?,?)";
    //修改客戶
    private static final String UPDATE_SQL = "UPDATE customers " + "SET id=?,password=?,address=?,phone=?,name=?,birthday=? WHERE email=?";
    //刪除客戶
    private static final String DELETE_SQL = "DELETE FROM customers WHERE email=?";

    //↓刪除客戶開始
    
    public void delete(Customer customer) throws GameBuyException {
        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(DELETE_SQL);) {
            pstmt.setString(1, customer.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new GameBuyException("刪除客戶失敗", ex);
        }

    }
    //↑刪除客戶結束

    //↓修改客戶開始
    
    public void update(Customer customer) throws GameBuyException {
        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(UPDATE_SQL);) {
            pstmt.setString(1, customer.getId());
            pstmt.setString(2, customer.getPassword());
            pstmt.setString(3, customer.getAddress());
            pstmt.setString(4, customer.getPhone());
            pstmt.setString(5, customer.getName());
            if (customer.getBirthday() != null) {
                pstmt.setDate(6, new java.sql.Date(customer.getBirthday().getTime()));
            } else {
                pstmt.setDate(6, null);
            }

            pstmt.setString(7, customer.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new GameBuyException("修改客戶失敗", ex);
        }

    }
    //↑修改客戶結束    

    //↓新增客戶開始
    
    public void insert(Customer customer) throws GameBuyException {
        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(INSERT_SQL);) {
            pstmt.setString(1, customer.getId());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getPassword());
            pstmt.setString(4, customer.getAddress());
            pstmt.setString(5, customer.getPhone());
            pstmt.setString(6, customer.getName());
            if (customer.getBirthday() != null) {
                pstmt.setDate(7, new java.sql.Date(customer.getBirthday().getTime()));
            } else {
                pstmt.setDate(7, null);
            }

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new GameBuyException("新增客戶失敗", ex);
        }

    }
    //↑新增客戶結束

    //↓查詢全部客戶
    
    public List<Customer> getAll() throws GameBuyException {
        List<Customer> list = new ArrayList<>();
        try (Connection connection = RDBConnection.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL);) {
            //↓while開始
            while (rs.next()) {
                Customer customer = new Customer();
                //↓try catch開始
                try {
                    //抓Customer裡的setter，然後getString(rs.資料庫的資料欄位名稱)
                    customer.setId(rs.getString("id"));
                    customer.setName(rs.getString("name"));
                    customer.setPassword(rs.getString("password"));
                    customer.setEmail(rs.getString("email"));
                    customer.setBirthday(rs.getDate("birthday"));
                    customer.setAddress(rs.getString("address"));
                    customer.setPhone(rs.getString("phone"));
                    customer.setStatus(rs.getInt("status"));
                    customer.setBonus(rs.getInt("bonus"));
                    //加到清單中
                    list.add(customer);
                } catch (GameBuyException ex) {
                    System.out.println("資料讀取錯誤" + ex);;
                }
                //↑try catch結束
            }
            //↑while結束
            System.out.println("CustomerList = " + list);
        } catch (SQLException ex) {
            throw new GameBuyException("查詢全部客戶失敗", ex);
        }

        return list;
    }
    //↑查詢全部客戶結束

    //↓查詢一個客戶
    
    public Customer get(String email) throws GameBuyException {
        //3.建立準備指令
        try (Connection connection = RDBConnection.getConnection(); PreparedStatement pstmt = connection.prepareStatement(SELECT_SQL);) {
            pstmt.setString(1, email);//傳SQL指令要的參數的值
            //4.執行指令並查詢
            try (ResultSet rs = pstmt.executeQuery()) {//executeQuary是拿來查資料,executeUpdate拿來修改刪除查詢
                Customer customer = new Customer();
                //↓while開始
                while (rs.next()) {//rs.next()一筆一筆往下指，如果有資料就回傳true,如果沒資料就回傳false並停止查詢資料
                    //↓try catch開始
                    try {
                        customer.setId(rs.getString("id"));//抓Customer裡的setter，然後getString(rs.資料庫的資料欄位名稱)
                        customer.setName(rs.getString("name"));
                        customer.setPassword(rs.getString("password"));
                        customer.setEmail(rs.getString("email"));
                        customer.setBirthday(rs.getDate("birthday"));
                        customer.setAddress(rs.getString("address"));
                        customer.setPhone(rs.getString("phone"));
                        customer.setStatus(rs.getInt("status"));
                        customer.setBonus(rs.getInt("bonus"));
                    } catch (GameBuyException ex) {
                        System.out.println("資料讀取錯誤" + ex);;
                    }
                    //↑try catch結束
                }
                //↑while結束
                System.out.println(customer);
                return customer;
            }
        } catch (SQLException ex) {
            throw new GameBuyException("查詢客戶失敗;" + email, ex);
        }
    }
    //↑查詢一個客戶結束

    
}
