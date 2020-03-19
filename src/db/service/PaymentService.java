package db.service;

import db.entity.Payment;
import db.repository.MerchantRepository;
import db.repository.PaymentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentService {

    PaymentRepository paymentRepo;
    MerchantRepository merchantRepo;

    public PaymentService ( PaymentRepository paymentRepo , MerchantRepository merchantRepo ) {
        this.paymentRepo = paymentRepo;
        this.merchantRepo = merchantRepo;
    }

    public void addNewPayment ( Payment newPay ) {
        Connection conn = paymentRepo.getCon ( );
        if ( newPay == null ) {
            return;
        } else if ( newPay.getChargePaid ( ) < 0 ) {
            return;
        }
        try (PreparedStatement ps = paymentRepo.addNewPayment ( newPay );
             PreparedStatement ps2 = merchantRepo.updateMerchantFromNewPayment ( newPay )) {
            conn.setAutoCommit ( false );
            try {
                // Insert new record into PAYMENT table
                ps.executeUpdate ( );
                // Update corresponding record in MERCHANT table
                ps2.executeUpdate ( );
                conn.commit ( );
            } catch (Exception e) {
                e.printStackTrace ( );
                conn.rollback ( );
            }
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }
}
