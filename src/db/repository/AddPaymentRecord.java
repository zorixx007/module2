package db.repository;

import db.entity.Payment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPaymentRecord {
    public static void add ( Payment newPay )  {
        try (Connection conn = MyConnection.getConnection ( );
             PreparedStatement ps = updatePayments ( conn , newPay );
             PreparedStatement ps2 = updateMerchant ( conn , newPay )) {
            conn.setAutoCommit ( false );
            try {
                // Insert new record into PAYMENT table
                ps.executeUpdate ( );
                // Update corresponding record in MERCHANT table
                ps2.executeUpdate ( );
                conn.commit ( );
            } catch (Exception e) {
                System.out.println ( e.getStackTrace ( ) );
                conn.rollback ( );
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace ( );
        }
    }

    private static PreparedStatement updatePayments ( Connection con , Payment newPay ) throws SQLException {
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

    private static PreparedStatement updateMerchant ( Connection con , Payment newPay ) throws SQLException {
        String sql = "UPDATE merchant SET needToSend = ? WHERE id = ?;";
        PreparedStatement ps = con.prepareStatement ( sql );
        Double newSum = newPay.getMerchant ( ).getNeedToSend ( ) + newPay.getChargePaid ( );
        ps.setDouble ( 1 , newSum );
        ps.setInt ( 2 , newPay.getMerchant ( ).getMerchantId ( ) );
        return ps;
    }


}
