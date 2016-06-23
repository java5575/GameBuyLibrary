/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.model;

import gamebuy.gb.domain.GameBuyException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Administrator
 */
public class RDBConnection {

    private static final String driver; // "com.mysql.jdbc.Driver";
    private static final String url; //"jdbc:mysql://localhost:3306/gamebuy?zeroDateTimeBehavior=convertToNull";
    private static final String userid; // "root";
    private static final String pwd; // "1234";

    static {
        //將連線資料存到properties用classpath的方式取得
        ResourceBundle bundle = null;
        try {
            bundle = ResourceBundle.getBundle("gamebuy.gb.model.dao");

        } catch (Exception ex) {
            System.out.println("無法讀取uuu.gamebuy.gb.dao.properties");
        }
        if (bundle != null) {
            String dr = bundle.getString("jdbc.driver");
            if (dr != null) {
                driver = dr;
            } else {
                driver = "com.mysql.jdbc.Driver";
            }

            String ur = bundle.getString("jdbc.url");
            if (ur != null) {
                url = ur;
            } else {
                url = "jdbc:mysql://localhost:3306/gamebuy?zeroDateTimeBehavior=convertToNull";
            }

            String us = bundle.getString("jdbc.userid");
            if (us != null) {
                userid = us;
            } else {
                userid = "root";
            }

            String pw = bundle.getString("jdbc.pwd");
            if (pw != null) {
                pwd = pw;
            } else {
                pwd = "1234";
            }

        } else {
            driver = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://localhost:3306/gamebuy?zeroDateTimeBehavior=convertToNull";
            userid = "root";
            pwd = "1234";
        }
    }

    public static Connection getConnection() throws GameBuyException {
        Context ctx;
        DataSource ds = null;
        Connection connection;
        try {
            //由server的connection pool取得連線
            ctx = new InitialContext();
            if (ctx == null) {
                throw new RuntimeException("JNDI Context是NULL");
            }

            ds = (DataSource) ctx.lookup("java:comp/env/connectionpool/gamebuy");// 在/connection之後名字自訂
            if (ds == null) {
                throw new RuntimeException("無法取得DataSource");
            }

            connection = ds.getConnection();
            System.out.println("Connection Pool:" + connection);
            return connection;
        } catch (NamingException | RuntimeException | SQLException nex) {//沒有加RuntimeException跟SQLExcpetion會沒辦法進入這段錯誤處理
            //沒有connection pool就改用原來的方式取得連線
            System.out.println("無法取得Naming Service: " + nex);
            try {
                //1.載入Driver類別
                Class.forName(driver);
                //2.建立Connection
                try {
                    connection = DriverManager.getConnection(url, userid, pwd);
                    System.out.println("Connection Pool:"+connection);
                    return connection;
                } catch (SQLException ex) {
                    throw new GameBuyException("無法建立連線", ex);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RDBConnection.class.getName()).log(Level.SEVERE, null, ex);
                throw new GameBuyException("無法載入JDBC Driver:" + driver, ex);
            }

        }
    }
}
