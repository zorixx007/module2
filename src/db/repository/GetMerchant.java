package db.repository;

import db.entity.Merchant;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;


public class GetMerchant {

    public static Merchant get ( int merchantId ) {
        Merchant current = null;
        try (Connection conn = MyConnection.getConnection ( );
             PreparedStatement ps = createPreparedStatement ( conn , merchantId );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return null;
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
        } catch (SQLException | IOException e) {
            e.printStackTrace ( );
        }
        return current;
    }

    private static PreparedStatement createPreparedStatement ( Connection con , int merchantId ) throws SQLException {
        String sql = "SELECT * FROM merchant where id = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , merchantId );
        return ps;
    }
}
