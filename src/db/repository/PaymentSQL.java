package db.repository;

import db.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentSQL {


    public static PreparedStatement paymentByMerchantID ( Connection con , int merchantId ) throws SQLException {
        String sql = "SELECT * FROM payment WHERE merchantId = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , merchantId );
        return ps;
    }

    public static PreparedStatement paymentByCustomerID ( Connection con , int customerId ) throws SQLException {
        String sql = "SELECT * FROM payment WHERE customerId = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , customerId );
        return ps;
    }

    public static PreparedStatement addNewPayment ( Connection con , Payment newPay ) throws SQLException {
        String sql = "INSERT INTO payment(dt, merchantId, customerId, goods, sumPaid, chargePaid) ";
        sql += " VALUES(?,?,?,?,?,?);";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setDate ( 1 , newPay.getDt ( ) );
        ps.setInt ( 2 , newPay.getMerchant ( ).getMerchantId ( ) );
        ps.setInt ( 3 , newPay.getCustomer ( ).getCustomerId ( ) );
        ps.setString ( 4 , newPay.getGoods ( ) );
        ps.setDouble ( 5 , newPay.getSumPaid ( ) );
        ps.setDouble ( 6 , newPay.getChargePaid ( ) );
        return ps;
    }

}
