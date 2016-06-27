/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import gamebuy.gb.domain.GameBuyException;
import gamebuy.gb.domain.GameType;
import gamebuy.gb.domain.PlatForm;
import gamebuy.gb.domain.Product;

/**
 *
 * @author Administrator
 */
public class RDBProductsDAO {

    private static final String COL_LIST = "name, unitprice, stock, url, description, bonus, status, preferentialprice, issuedate, platform_idplatform, gametype_idgametype";
    private static final String SELECT_SQL = "SELECT id, " + COL_LIST + " FROM products WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT id, " + COL_LIST + " FROM products";

    private static final String INSERT_SQL
            = "INSERT INTO products (" + COL_LIST + ") VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_WITH_ID_SQL
            = "INSERT INTO products (" + COL_LIST + ",id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String UPDATE_SQL = "UPDATE products SET name=?,unitprice=?,stock,url=?,description=?,bonus=?,status=?,preferentialprice=?,"
            + "issuedate=?,platform=?,gametype=? WHERE id=?";
    private static final String DELETE_SQL = "DELETE FROM products WHERE id=?";
    private static final String IN_SQL = "SELECT gametype FROM products WHERE gametype IN ?";

    public void insert(Product p) throws GameBuyException {
        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                        p.getId() == 0 ? INSERT_SQL : INSERT_WITH_ID_SQL,
                        p.getId() == 0 ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);) {

            pstmt.setString(1, p.getName());
            pstmt.setDouble(2, p.getUnitPrice());
            pstmt.setInt(3, p.getStock());
            pstmt.setString(4, p.getUrl());
            pstmt.setString(5, p.getDescription());
            pstmt.setInt(6, p.getBonus());
            pstmt.setInt(7, p.getStatus());//status
            pstmt.setDouble(8, p.getPreferentialPrice());
            pstmt.setDate(9, new java.sql.Date(p.getIssueDate().getTime()));
            pstmt.setInt(10, p.getPlatForm().ordinal());//platform
            pstmt.setInt(11, p.getGameType().ordinal());//gametype

            if (p.getId() > 0) {
                pstmt.setInt(8, p.getId());
            }
            pstmt.executeUpdate();

            if (p.getId() == 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys();) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        p.setId(id);
                    }
                }
            }

        } catch (SQLException ex) {
            throw new GameBuyException("新增產品失敗!", ex);
        }
    }

    public void update(Product p) throws GameBuyException {
        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(UPDATE_SQL);) {
            pstmt.setString(1, p.getName());
            pstmt.setDouble(2, p.getUnitPrice());
            pstmt.setInt(3, p.getStock());
            pstmt.setString(4, p.getUrl());
            pstmt.setString(5, p.getDescription());
            pstmt.setInt(6, p.getBonus());
            pstmt.setString(7, p.getClass().getSimpleName());//status
            pstmt.setDouble(8, p.getPreferentialPrice());
            pstmt.setDate(9, new java.sql.Date(p.getIssueDate().getTime()));
            pstmt.setInt(10, p.getPlatForm().ordinal());//platform
            pstmt.setInt(11, p.getGameType().ordinal());//gametype

            pstmt.setInt(12, p.getId());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new GameBuyException("修改產品失敗!", ex);
        }
    }

    public void delete(Product p) throws GameBuyException {
        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(DELETE_SQL);) {
            pstmt.setInt(1, p.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new GameBuyException("刪除產品失敗!", ex);
        }
    }

    public List<Product> getAll() throws GameBuyException {
        List<Product> list = new ArrayList<>();
        try (Connection connection = RDBConnection.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL);) {
            while (rs.next()) {

                Product p;
                p = new Product();
                try {
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setUnitPrice(rs.getDouble("unitprice"));
                    p.setStock(rs.getInt("stock"));
                    p.setUrl(rs.getString("url"));
                    p.setDescription(rs.getString("description"));
                    p.setBonus(rs.getInt("bonus"));
                    p.setStatus(rs.getInt("status"));
                    p.setPreferentialPrice(rs.getDouble("preferentialprice"));
                    p.setIssueDate(rs.getDate("issuedate"));

                    Integer platForm = rs.getInt("platform_idplatform");
                    p.setPlatForm(PlatForm.values()[platForm]);

                    Integer gameType = rs.getInt("gametype_idgametype");
                    p.setGameType(GameType.values()[gameType]);

                    list.add(p);
                } catch (RuntimeException ex) {
                    System.out.println("產品資料讀取錯誤: " + ex);
                }
            }
            return list;
        } catch (SQLException ex) {
            throw new GameBuyException("查詢全部產品失敗!", ex);
        }
    }

    public Product get(int id) throws GameBuyException {
        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(SELECT_SQL)) {
            pstmt.setInt(1, id);//傳SQL指令
            try (ResultSet rs = pstmt.executeQuery();) {//執行指令並取得結果
                //處理ResultSet的內容
                Product p = new Product();
                while (rs.next()) {
                    try {
                        p.setId(rs.getInt("id"));
                        p.setName(rs.getString("name"));
                        p.setUnitPrice(rs.getDouble("unitprice"));
                        p.setStock(rs.getInt("stock"));
                        p.setUrl(rs.getString("url"));
                        p.setDescription(rs.getString("description"));
                        p.setBonus(rs.getInt("bonus"));
                        p.setStatus(rs.getInt("status"));
                        p.setPreferentialPrice(rs.getDouble("preferentialprice"));
                        p.setIssueDate(rs.getDate("issuedate"));
//
                        Integer platForm = rs.getInt("platform_idplatform");
                        p.setPlatForm(PlatForm.values()[platForm]);

                        Integer gameType = rs.getInt("gametype_idgametype");
                        p.setGameType(GameType.values()[gameType]);

                    } catch (RuntimeException ex) {
                        System.out.println("產品資料讀取錯誤: " + ex);
                    }
                }
//                System.out.println(p);
                return p;
            }
        } catch (SQLException ex) {
            throw new GameBuyException("查詢產品失敗: " + id, ex);
        }
    }

    public List<Product> in(String gametype) throws GameBuyException {
        List<Product> list = new ArrayList<>();
        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(IN_SQL);) {
            pstmt.setString(1, '(' + gametype + ')');//傳SQL指令
            try (ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    Product p = new Product();
                    try {

                        p.setId(rs.getInt("id"));
                        p.setName(rs.getString("name"));
                        p.setUnitPrice(rs.getDouble("unitprice"));
                        p.setStock(rs.getInt("stock"));
                        p.setUrl(rs.getString("url"));
                        p.setDescription(rs.getString("description"));
                        p.setBonus(rs.getInt("bonus"));
                        p.setStatus(rs.getInt("status"));
                        p.setPreferentialPrice(rs.getDouble("preferentialprice"));
                        p.setIssueDate(rs.getDate("issuedate"));

                        Integer platForm = rs.getInt("platform_idplatform");
                        p.setPlatForm(PlatForm.values()[platForm]);

                        Integer gameType = rs.getInt("gametype_idgametype");
                        p.setGameType(GameType.values()[gameType]);
                        list.add(p);
                    } catch (RuntimeException ex) {
                        System.out.println("產品資料讀取錯誤: " + ex);
                    }
                }
            }
            return list;
        } catch (SQLException ex) {
            throw new GameBuyException("查詢產品失敗" + ex);
        }
    }
}
