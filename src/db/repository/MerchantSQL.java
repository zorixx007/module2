package db.repository;

import db.entity.Merchant;
import db.entity.Payment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;


public class MerchantSQL {

    private static PreparedStatement psGetMerchantByID ( Connection con , int merchantId ) throws SQLException {
        String sql = "SELECT * FROM merchant where id = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , merchantId );
        return ps;
    }

    private static PreparedStatement psGetAllMerchants ( Connection con ) throws SQLException {
        String sql = "SELECT * FROM merchant;";
        PreparedStatement ps = con.prepareStatement ( sql );
        return ps;
    }

    private static PreparedStatement psGetAllMerchantsSorted ( Connection con ) throws SQLException {
        String sql = "SELECT * FROM merchant order by name desc;";
        PreparedStatement ps = con.prepareStatement ( sql );
        return ps;
    }

    private static PreparedStatement psGetNeedToSend ( Connection con , Merchant merchant ) throws SQLException {
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

    public static void payToMerchant ( Connection conn , Merchant merchant , double sumToPay ) {
        try (PreparedStatement ps = psGetNeedToSend ( conn , merchant );
             ResultSet rs = ps.executeQuery ( )) {
            conn.setAutoCommit ( false );
            if ( !rs.next ( ) ) {
                return;
            }
            double currentSum = rs.getDouble ( "needToSend" );
            try (PreparedStatement ps2 = MerchantSQL.updatePayToMerchant ( conn , merchant , ( currentSum - sumToPay ) , sumToPay )) {
                ps2.executeUpdate ( );
            } catch (SQLException e) {
                e.printStackTrace ( );
            }
            conn.commit ( );
        } catch (Exception e) {
            e.printStackTrace ( );
            try {
                conn.rollback ( );
            } catch (SQLException ex) {
                ex.printStackTrace ( );
                throw new RuntimeException ( ex );
            }
        }
    }

    public static Merchant getMerchantByID ( Connection conn , int merchantId ) {
        Merchant current = null;
        try (PreparedStatement ps = psGetMerchantByID ( conn , merchantId );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return current;
            }
            Date date = rs.getDate ( "lastSent" );
            LocalDate ld = null;
            if ( date != null ) {
                ld = date.toLocalDate ( );
            }
            current = new Merchant ( rs.getInt ( "id" ) , rs.getString ( "name" ) ,
                    rs.getString ( "bankName" ) , rs.getString ( "swift" ) , rs.getString ( "account" ) ,
                    rs.getDouble ( "charge" ) , rs.getInt ( "period" ) , rs.getDouble ( "minSum" ) ,
                    rs.getDouble ( "needToSend" ) , rs.getDouble ( "sent" ) , ld );
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        return current;
    }

    public static ArrayList<Merchant> getAllMerchants ( Connection conn ) {
        ArrayList<Merchant> current = new ArrayList<> ( );
        try (PreparedStatement ps = psGetAllMerchants ( conn );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return current;
            } else {
                do {
                    Date date = rs.getDate ( "lastSent" );
                    LocalDate ld = null;
                    if ( date != null ) {
                        ld = date.toLocalDate ( );
                    }
                    current.add ( new Merchant ( rs.getInt ( "id" ) , rs.getString ( "name" ) ,
                            rs.getString ( "bankName" ) , rs.getString ( "swift" ) , rs.getString ( "account" ) ,
                            rs.getDouble ( "charge" ) , rs.getInt ( "period" ) , rs.getDouble ( "minSum" ) ,
                            rs.getDouble ( "needToSend" ) , rs.getDouble ( "sent" ) , ld ) );
                } while (rs.next ( ));
            }
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        return current;
    }

    public static LinkedList<Merchant> getAllMerchantsSorted ( Connection conn ) {
        LinkedList<Merchant> current = new LinkedList<> ( );
        try (PreparedStatement ps = psGetAllMerchantsSorted ( conn );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return null;
            } else {
                do {
                    Date date = rs.getDate ( "lastSent" );
                    LocalDate ld = null;
                    if ( date != null ) {
                        ld = date.toLocalDate ( );
                    }
                    current.add ( new Merchant ( rs.getInt ( "id" ) , rs.getString ( "name" ) ,
                            rs.getString ( "bankName" ) , rs.getString ( "swift" ) , rs.getString ( "account" ) ,
                            rs.getDouble ( "charge" ) , rs.getInt ( "period" ) , rs.getDouble ( "minSum" ) ,
                            rs.getDouble ( "needToSend" ) , rs.getDouble ( "sent" ) , ld ) );
                } while (rs.next ( ));
            }
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        return current;
    }


}
