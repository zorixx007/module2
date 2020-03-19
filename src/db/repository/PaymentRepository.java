package db.repository;

import db.entity.Customer;
import db.entity.Merchant;
import db.entity.Payment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentRepository {
    CustomerRepository customerRepo;
    MerchantRepository merchantRepo;
    DBUtils connectionToDB;
    Connection con = null;

    public PaymentRepository ( CustomerRepository customerRepo , MerchantRepository merchantRepo , DBUtils connectionToDB ) {
        this.customerRepo = customerRepo;
        this.merchantRepo = merchantRepo;
        this.connectionToDB = connectionToDB;
        try {
            con = connectionToDB.getConnection ( );
        } catch (IOException | SQLException e) {
            e.printStackTrace ( );
        }
    }


    public Connection getCon () {
        return con;
    }

    public PreparedStatement psPaymentByMerchantID ( int merchantId ) throws SQLException {
        String sql = "SELECT id, dt, merchantId, customerId, goods, sumPaid, chargePaid FROM payment WHERE merchantId = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , merchantId );
        return ps;
    }

    public PreparedStatement psPaymentByCustomerID ( int customerId ) throws SQLException {
        String sql = "SELECT id, dt, merchantId, customerId, goods, sumPaid, chargePaid FROM payment WHERE customerId = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , customerId );
        return ps;
    }

    public PreparedStatement psPaymentByPeriod ( int lastNDays ) throws SQLException {
        String sql = "SELECT id, dt, merchantId, customerId, goods, sumPaid, chargePaid FROM payment WHERE dt >= now() - INTERVAL ? DAY";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , lastNDays );
        return ps;
    }

    public PreparedStatement addNewPayment ( Payment newPay ) throws SQLException {
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

    public ArrayList<Payment> getPaymentsForMerchant ( Merchant merchant ) {
        ArrayList<Payment> payments = new ArrayList<> ( );
        if ( merchant == null ) {
            return payments;
        }
        try (PreparedStatement ps = psPaymentByMerchantID ( merchant.getMerchantId ( ) );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return payments;
            } else {
                do {
                    Payment current = new Payment ( rs.getInt ( "id" ) ,
                            rs.getDate ( "dt" ) ,
                            merchant ,
                            customerRepo.getCustomerByID ( rs.getInt ( "customerId" ) ) ,
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


    public ArrayList<Payment> getPaymentsForCustomer ( Customer customer ) {
        ArrayList<Payment> payments = new ArrayList<> ( );
        if ( customer == null ) {
            return payments;
        }
        try (PreparedStatement ps = psPaymentByCustomerID ( customer.getCustomerId ( ) );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return payments;
            } else {
                do {
                    Payment current = new Payment ( rs.getInt ( "id" ) ,
                            rs.getDate ( "dt" ) ,
                            merchantRepo.getMerchantByID ( rs.getInt ( "merchantId" ) ) ,
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


    public ArrayList<Payment> getPaymentsByPeriod ( int lastNDays ) {
        ArrayList<Payment> payments = new ArrayList<> ( );
        try (PreparedStatement ps = psPaymentByPeriod ( lastNDays );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return payments;
            } else {
                do {
                    Payment current = new Payment ( rs.getInt ( "id" ) ,
                            rs.getDate ( "dt" ) ,
                            merchantRepo.getMerchantByID ( rs.getInt ( "merchantId" ) ) ,
                            customerRepo.getCustomerByID ( rs.getInt ( "customerId" ) ) ,
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

    public void addPayment ( Payment newPay ) {
        Connection conn = getCon ( );
        if ( newPay == null ) {
            return;
        } else if ( newPay.getChargePaid ( ) < 0 ) {
            return;
        }
        try (PreparedStatement ps = addNewPayment ( newPay );
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