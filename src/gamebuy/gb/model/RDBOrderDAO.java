/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.model;

import gamebuy.gb.domain.GameBuyException;
import gamebuy.gb.domain.Order;
import gamebuy.gb.domain.OrderItem;
import gamebuy.gb.domain.PaymentType;
import gamebuy.gb.domain.ShippingType;
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
            + "receiver_phone,status,bonus,new_bonus";
    private static final String INSERT_ORDERS_SQL = "INSERT INTO orders (" + COL_LIST
            + ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_ORDERITEM_SQL = "INSERT INTO order_item ("
            + "order_id,product_id,price,quantity,new_bouns"
            + ") VALUES(?,?,?,?,?)";
    private static final String SELECT_ORDERS_HISTORY_BY_CUSTOMER_ID
            = "SELECT orders.id,orders.created_time,orders.status,"
            + "payment_type,payment_fee,shipping_fee,shipping_type,shipping_address,"
            + "receiver_name,receiver_email,receiver_phone,bonus,"
            + "sum(price*quantity) as total_amount,sum(new_bonus*quantity) as total_bonus FROM orders "
            + "INNER JOIN order_item ON orders.id = order_item.order_id "
            + "WHERE customer_email = ? "
            + "GROUP BY order_id";

    public void create(Order order) throws GameBuyException {
        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt
                = connection.prepareStatement(INSERT_ORDERS_SQL, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement pstmt2 = connection.prepareStatement(INSERT_ORDERITEM_SQL);) {

            connection.setAutoCommit(false);
            try {
                //新增訂單主檔
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
                pstmt.setInt(15, order.getNewBonus()); 
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
                    pstmt2.setDouble(3, item.getProduct().getPreferentialPrice());
                    pstmt2.setInt(4, item.getQuantity());
                    pstmt2.setInt(5, item.getNewBonus());
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
    
    public void updateStatus(int oldStatus, Order order) throws GameBuyException {
        
    }

    public Order get(Integer id) throws GameBuyException {
        Order order = createOrderObjrct(null);
        return order;
    }

    public List<Order> getByCustomer(String customerEmail) throws GameBuyException {
        List<Order> list = new ArrayList<>();
        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ORDERS_HISTORY_BY_CUSTOMER_ID)) {
            pstmt.setString(1, ""+ customerEmail + "");
            try (ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()){
                    Order o = createOrderObjrct(null);
                    
                    o.setId(rs.getInt("id"));
                    o.setCreatedTime(rs.getTimestamp("created_time"));
                    o.setStatus(rs.getInt("status"));
                    
                    o.setPaymentType(PaymentType.values()[rs.getInt("payment_type")]);
                    o.setPaymentFee(rs.getDouble("payment_fee"));
                    o.setShippingType(ShippingType.values()[rs.getInt("shipping_type")]);
                    o.setShippingFee(rs.getDouble("shipping_fee"));
                    o.setReceiverName(rs.getString("receiver_name"));
                    o.setReceiverEmail(rs.getString("receiver_email"));
                    o.setReceiverPhone(rs.getString("receiver_phone"));
                    o.setShippingAddress(rs.getString("shipping_address"));
                    o.setTotalAmount(rs.getDouble("total_amount"));
                    o.setBonus(rs.getInt("bonus"));
                    o.setNewBonus(rs.getInt("total_bonus"));
                    list.add(o);
                }
                return list;
            }
        } catch (SQLException ex) {
            throw new GameBuyException("查詢客戶[" + customerEmail + "]歷史訂單失敗!", ex);
        }

    }
    private Order createOrderObjrct(String type){
        return new Order();
    }
}
