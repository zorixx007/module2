package db.service;

import db.entity.Customer;
import db.entity.Merchant;
import db.entity.Payment;
import db.repository.MerchantSQL;
import db.repository.PaymentSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentService {

    public static ArrayList<Payment> getPaymentsForMerchant ( Connection conn , Merchant merchant ) {
        ArrayList<Payment> payments = new ArrayList<> ( );
        if ( merchant == null ) {
            return payments;
        }
        try (PreparedStatement ps = PaymentSQL.paymentByMerchantID ( conn , merchant.getMerchantId ( ) );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return payments;
            } else {
                do {
                    Payment current = new Payment ( rs.getInt ( "id" ) ,
                            rs.getDate ( "dt" ) ,
                            merchant ,
                            CustomerService.getCustomerByID ( conn , rs.getInt ( "customerId" ) ) ,
                            rs.getString ( "goods" ) ,
                            rs.getDouble ( "sumPaid" ) ,
                            rs.getDouble ( "chargePaid" ) );
                    payments.add ( current );
                } while (rs.next ( ));
            }
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        return payments;
    }


    public static ArrayList<Payment> getPaymentsForCustomer ( Connection conn , Customer customer ) {
        ArrayList<Payment> payments = new ArrayList<> ( );
        if ( customer == null ) {
            return payments;
        }
        try (PreparedStatement ps = PaymentSQL.paymentByCustomerID ( conn , customer.getCustomerId ( ) );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return null;
            } else {
                do {
                    Payment current = new Payment ( rs.getInt ( "id" ) ,
                            rs.getDate ( "dt" ) ,
                            MerchantService.getMerchantByID ( conn , rs.getInt ( "merchantId" ) ) ,
                            customer ,
                            rs.getString ( "goods" ) ,
                            rs.getDouble ( "sumPaid" ) ,
                            rs.getDouble ( "chargePaid" ) );
                    payments.add ( current );
                } while (rs.next ( ));
            }
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        return payments;
    }

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
