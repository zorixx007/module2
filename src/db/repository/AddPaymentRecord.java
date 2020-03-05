package db.repository;

import db.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPaymentRecord {
    public static void add ( Connection conn , Payment newPay ) throws SQLException {
        String sql = " INSERT INTO payment(dt, merchantId, customerId, goods, sumPaid, chargePaid) ";
        sql += " VALUES(?,?,?,?,?,?);";
        PreparedStatement stmt1 = conn.prepareStatement ( sql );
        stmt1.setDate ( 1 , newPay.getDt ( ) );
        stmt1.setInt ( 2 , newPay.getMerchantId ( ) );
        stmt1.setInt ( 3 , newPay.getCustomerId ( ) );
        stmt1.setString ( 4 , newPay.getGoods ( ) );
        stmt1.setDouble ( 5 , newPay.getSumPaid ( ) );
        stmt1.setDouble ( 6 , newPay.getChargePaid ( ) );
        stmt1.executeUpdate ( );
        stmt1.close ( );
    }
}
