package db.service;

import db.entity.Payment;
import db.repository.MerchantSQL;
import db.repository.PaymentSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentService {

    public static void addPayment ( Connection conn , Payment newPay ) {
        if ( newPay == null ) {
            return;
        } else if ( newPay.getChargePaid ( ) < 0 ) {
            return;
        }
        try (PreparedStatement ps = PaymentSQL.addNewPayment ( conn , newPay );
             PreparedStatement ps2 = MerchantSQL.updateMerchantFromNewPayment ( conn , newPay )) {
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
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }


}
