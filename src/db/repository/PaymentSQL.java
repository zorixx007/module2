package db.repository;

import db.entity.Customer;
import db.entity.Merchant;
import db.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentSQL {


    private static PreparedStatement psPaymentByMerchantID ( Connection con , int merchantId ) throws SQLException {
        String sql = "SELECT * FROM payment WHERE merchantId = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , merchantId );
        return ps;
    }

    private static PreparedStatement psPaymentByCustomerID ( Connection con , int customerId ) throws SQLException {
        String sql = "SELECT * FROM payment WHERE customerId = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , customerId );
        return ps;
    }

    private static PreparedStatement psPaymentByPeriod ( Connection con , int lastNDays ) throws SQLException {
        String sql = "SELECT * FROM payment WHERE dt >= now() - INTERVAL ? DAY";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , lastNDays );
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


    public static ArrayList<Payment> getPaymentsForMerchant ( Connection conn , Merchant merchant ) {
        ArrayList<Payment> payments = new ArrayList<> ( );
        if ( merchant == null ) {
            return payments;
        }
        try (PreparedStatement ps = psPaymentByMerchantID ( conn , merchant.getMerchantId ( ) );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return payments;
            } else {
                do {
                    Payment current = new Payment ( rs.getInt ( "id" ) ,
                            rs.getDate ( "dt" ) ,
                            merchant ,
                            CustomerSQL.getCustomerByID ( conn , rs.getInt ( "customerId" ) ) ,
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
        try (PreparedStatement ps = psPaymentByCustomerID ( conn , customer.getCustomerId ( ) );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return payments;
            } else {
                do {
                    Payment current = new Payment ( rs.getInt ( "id" ) ,
                            rs.getDate ( "dt" ) ,
                            MerchantSQL.getMerchantByID ( conn , rs.getInt ( "merchantId" ) ) ,
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


    public static ArrayList<Payment> getPaymentsByPeriod ( Connection conn , int lastNDays ) {
        ArrayList<Payment> payments = new ArrayList<> ( );
        try (PreparedStatement ps = psPaymentByPeriod ( conn , lastNDays );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return payments;
            } else {
                do {
                    Payment current = new Payment ( rs.getInt ( "id" ) ,
                            rs.getDate ( "dt" ) ,
                            MerchantSQL.getMerchantByID ( conn , rs.getInt ( "merchantId" ) ) ,
                            CustomerSQL.getCustomerByID ( conn , rs.getInt ( "customerId" ) ) ,
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


}
