package db.service;

import db.entity.Merchant;
import db.repository.MerchantSQL;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

public class MerchantService {
    public static Merchant getMerchantByID ( Connection conn , int merchantId ) {
        Merchant current = null;
        try (PreparedStatement ps = MerchantSQL.getMerchantByID ( conn , merchantId );
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
        try (PreparedStatement ps = MerchantSQL.getAllMerchants ( conn );
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
        try (PreparedStatement ps = MerchantSQL.getAllMerchantsSorted ( conn );
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
