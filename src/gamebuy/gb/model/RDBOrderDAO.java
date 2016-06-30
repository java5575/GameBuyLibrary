/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.model;

import gamebuy.gb.domain.GameBuyException;
import gamebuy.gb.domain.Order;
import gamebuy.gb.domain.OrderItem;
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
public class RDBOrderDAO {

    private static final String COL_LIST = "customer_email,created_time,payment_type,payment_fee,payment_note,"
            + "shipping_type,shipping_fee,shipping_note,shipping_address,receiver_name,receiver_email,"
            + "receiver_phone,status,bonus";
    private static final String INSERT_ORDERS_SQL = "INSERT INTO orders (" + COL_LIST
            + ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_ORDERITEM_SQL = "INSERT INTO order_item ("
            + "order_id,product_id,price,quantity"
            + ") VALUES(?,?,?,?)";

    public void create(Order order) throws GameBuyException {
        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt
                = connection.prepareStatement(INSERT_ORDERS_SQL, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement pstmt2 = connection.prepareStatement(INSERT_ORDERITEM_SQL);) {
            //新增訂單主檔
            connection.setAutoCommit(false);
            try {
                pstmt.setString(1, order.getCustomer().getEmail());
                pstmt.setTimestamp(2, new java.sql.Timestamp(order.getCreatedTime().getTime()));
                pstmt.setInt(3, order.getPaymentType().ordinal());
                pstmt.setDouble(4, order.getPaymentFee());
                pstmt.setString(5, order.getPaymentNote());
                pstmt.setInt(6, order.getShippingType().ordinal());
                pstmt.setDouble(7, order.getShippingFee());
                pstmt.setString(8, order.getShippingNote());
                pstmt.setString(9, order.getShippingAddress());
                pstmt.setString(10, order.getReceiverName());
                pstmt.setString(11, order.getReceiverEmail());
                pstmt.setString(12, order.getReceiverPhone());
                pstmt.setInt(13, order.getStatus());
                pstmt.setInt(14, order.getBonus());

                pstmt.executeUpdate();

                //取得DB自動給號的訂單編號
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    while (rs.next()) {
                        order.setId(rs.getInt(1));
                    }
                }
                //新增訂單明細
                for (OrderItem item : order.getOrderItemLst()) {

                    pstmt2.setInt(1, order.getId());
                    pstmt2.setInt(2, item.getProduct().getId());
                    pstmt2.setDouble(3, item.getPrice());
                    pstmt2.setInt(4, item.getQuantity());
                    pstmt2.executeUpdate();

                }
                connection.commit();
            } catch (SQLException ex) {
                connection.rollback();
                throw ex;
            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            throw new GameBuyException("訂單新增失敗!", ex);
        }
    }

    public void update(Order c) throws GameBuyException {

    }

    public void delete(Order c) throws GameBuyException {

    }

    public Order get(Integer id) throws GameBuyException {
        Order order = null;
        return order;
    }

    public List<Order> getByCustomer(String customerId) {
        List<Order> list = new ArrayList<>();

        return list;
    }
}
