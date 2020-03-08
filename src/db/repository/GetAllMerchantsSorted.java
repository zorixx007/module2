package db.repository;

import db.entity.Merchant;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class GetAllMerchantsSorted {
    public static LinkedList<Merchant> get () {
        LinkedList<Merchant> current = new LinkedList<> ( );
        try (Connection conn = MyConnection.getConnection ( );
             PreparedStatement ps = createPreparedStatement ( conn );
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
        } catch (SQLException | IOException e) {
            e.printStackTrace ( );
        }
        return current;
    }

    private static PreparedStatement createPreparedStatement ( Connection con ) throws SQLException {
        String sql = "SELECT * FROM merchant order by name desc;";
        PreparedStatement ps = con.prepareStatement ( sql );
        return ps;
    }
}
