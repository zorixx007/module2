package db.repository;

import db.entity.Merchant;
import db.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MerchantSQL {

    public static PreparedStatement getMerchantByID ( Connection con , int merchantId ) throws SQLException {
        String sql = "SELECT * FROM merchant where id = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , merchantId );
        return ps;
    }

    public static PreparedStatement getAllMerchants ( Connection con ) throws SQLException {
        String sql = "SELECT * FROM merchant;";
        PreparedStatement ps = con.prepareStatement ( sql );
        return ps;
    }

    public static PreparedStatement getAllMerchantsSorted ( Connection con ) throws SQLException {
        String sql = "SELECT * FROM merchant order by name desc;";
        PreparedStatement ps = con.prepareStatement ( sql );
        return ps;
    }

    public static PreparedStatement getNeedToSend ( Connection con , Merchant merchant ) throws SQLException {
        String sql = "SELECT needToSend FROM merchant where id = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , merchant.getMerchantId ( ) );
        return ps;
    }

    public static PreparedStatement updatePayToMerchant ( Connection con , Merchant merchant , double currentSum , double sentSum ) throws SQLException {
        String sql = "UPDATE merchant SET needToSend = ?, sent = ?, lastSent = now() WHERE id = ?;";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setDouble ( 1 , currentSum );
        ps.setDouble ( 2 , sentSum );
        ps.setInt ( 3 , merchant.getMerchantId ( ) );
        return ps;
    }

    public static PreparedStatement updateMerchantFromNewPayment ( Connection con , Payment newPay ) throws SQLException {
        String sql = "UPDATE merchant SET needToSend = ? WHERE id = ?;";
        PreparedStatement ps = con.prepareStatement ( sql );
        Double newSum = newPay.getMerchant ( ).getNeedToSend ( ) + newPay.getChargePaid ( );
        ps.setDouble ( 1 , newSum );
        ps.setInt ( 2 , newPay.getMerchant ( ).getMerchantId ( ) );
        return ps;
    }

}
