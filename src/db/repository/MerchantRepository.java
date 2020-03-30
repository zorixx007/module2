package db.repository;

import db.entity.Merchant;
import db.entity.Payment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class MerchantRepository {

    Connection con;
    private HashMap<Integer, Merchant> cacheMerchantByID = new HashMap<> ( );


    public MerchantRepository ( Connection con ) {
        this.con = con;
    }

    public Connection getCon () {
        return con;
    }

    public PreparedStatement psGetMerchantByID ( int merchantId ) throws SQLException {
        String sql = "SELECT id, name, bankName, swift, account, charge, period, minSum, needToSend, sent, lastSent FROM merchant where id = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , merchantId );
        return ps;
    }

    public Merchant getMerchantByID ( int merchantId ) {
        Merchant current = null;
        if ( cacheMerchantByID.containsKey ( merchantId ) ) {
            current = cacheMerchantByID.get ( merchantId );
        } else {
            try (PreparedStatement ps = psGetMerchantByID ( merchantId );
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
            cacheMerchantByID.put ( merchantId , current );
        }
        return current;
    }


    public PreparedStatement psGetMerchantByName ( String merchantName ) throws SQLException {
        String sql = "SELECT id, name, bankName, swift, account, charge, period, minSum, needToSend, sent, lastSent FROM merchant where name = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setString ( 1 , merchantName );
        return ps;
    }

    public Merchant getMerchantByName ( String merchantName ) {
        Merchant current = null;
        try (PreparedStatement ps = psGetMerchantByName ( merchantName );
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

    public PreparedStatement psGetAllMerchants () throws SQLException {
        String sql = "SELECT id, name, bankName, swift, account, charge, period, minSum, needToSend, sent, lastSent FROM merchant;";
        PreparedStatement ps = con.prepareStatement ( sql );
        return ps;
    }

    public PreparedStatement psGetAllMerchantsSorted () throws SQLException {
        String sql = "SELECT id, name, bankName, swift, account, charge, period, minSum, needToSend, sent, lastSent FROM merchant order by name desc;";
        PreparedStatement ps = con.prepareStatement ( sql );
        return ps;
    }

    public PreparedStatement psGetNeedToSend ( Merchant merchant ) throws SQLException {
        String sql = "SELECT needToSend FROM merchant where id = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , merchant.getMerchantId ( ) );
        return ps;
    }

    public PreparedStatement updatePayToMerchant ( Merchant merchant , double currentSum , double sentSum ) throws SQLException {
        String sql = "UPDATE merchant SET needToSend = ?, sent = ?, lastSent = now() WHERE id = ?;";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setDouble ( 1 , currentSum );
        ps.setDouble ( 2 , sentSum );
        ps.setInt ( 3 , merchant.getMerchantId ( ) );
        return ps;
    }

    public PreparedStatement updateMerchantFromNewPayment ( Payment newPay ) throws SQLException {
        String sql = "UPDATE merchant SET needToSend = ? WHERE id = ?;";
        PreparedStatement ps = con.prepareStatement ( sql );
        Double newSum = newPay.getMerchant ( ).getNeedToSend ( ) + newPay.getChargePaid ( );
        ps.setDouble ( 1 , newSum );
        ps.setInt ( 2 , newPay.getMerchant ( ).getMerchantId ( ) );
        return ps;
    }

    public PreparedStatement psAddNewMerchant ( Merchant newMerchant ) throws SQLException {
        String sql = "INSERT INTO merchant (name, bankName, swift, account, charge, period, minSum, needToSend, sent, lastSent  ) ";
        sql += " VALUES(?,?,?,?,?,?,?,?,?,?);";
        Date ld = null;
        if ( newMerchant.getLastSent ( ) != null ) {
            ld = Date.valueOf ( newMerchant.getLastSent ( ) );
        }
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setString ( 1 , newMerchant.getName ( ) );
        ps.setString ( 2 , newMerchant.getBankName ( ) );
        ps.setString ( 3 , newMerchant.getSwift ( ) );
        ps.setString ( 4 , newMerchant.getAccount ( ) );
        ps.setDouble ( 5 , newMerchant.getCharge ( ) );
        ps.setInt ( 6 , newMerchant.getPeriod ( ) );
        ps.setDouble ( 7 , newMerchant.getMinSum ( ) );
        ps.setDouble ( 8 , newMerchant.getNeedToSend ( ) );
        ps.setDouble ( 9 , newMerchant.getSent ( ) );
        ps.setDate ( 10 , ld );
        return ps;
    }

    public void AddNewMerchant ( Merchant newMerchant ) {
        try (PreparedStatement ps = psAddNewMerchant ( newMerchant )) {
            ps.executeUpdate ( );
        } catch (Exception e) {
            e.printStackTrace ( );
        }
    }

    public boolean uploadMerchantListToDB ( ArrayList<Merchant> merchantList ) {
        Connection conn = getCon ( );
        try {
            conn.setAutoCommit ( false );
            for (Merchant merchant : merchantList) {
                AddNewMerchant ( merchant );
            }
            conn.commit ( );
        } catch (SQLException e) {
            e.printStackTrace ( );
            try {
                conn.rollback ( );
            } catch (SQLException ex) {
                ex.printStackTrace ( );
            }
            return false;
        }
        return true;
    }

    public void payToMerchant ( Merchant merchant , double sumToPay ) {
        Connection conn = getCon ( );
        try (PreparedStatement ps = psGetNeedToSend ( merchant );
             ResultSet rs = ps.executeQuery ( )) {
            conn.setAutoCommit ( false );
            if ( !rs.next ( ) ) {
                return;
            }
            double currentSum = rs.getDouble ( "needToSend" );
            try (PreparedStatement ps2 = updatePayToMerchant ( merchant , ( currentSum - sumToPay ) , sumToPay )) {
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


    public ArrayList<Merchant> getAllMerchants () {
        ArrayList<Merchant> current = new ArrayList<> ( );
        try (PreparedStatement ps = psGetAllMerchants ( );
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

    public LinkedList<Merchant> getAllMerchantsSorted () {
        LinkedList<Merchant> current = new LinkedList<> ( );
        try (PreparedStatement ps = psGetAllMerchantsSorted ( );
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
