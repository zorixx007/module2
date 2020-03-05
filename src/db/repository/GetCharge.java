package db.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetCharge {
    public static double get ( int merchantId ) {
        double ret = 0;
        try (Connection conn = MyConnection.getConnection ( );
             Statement stmt = conn.createStatement ( );
             ResultSet rs = stmt.executeQuery ( "SELECT charge FROM merchant where id = " + merchantId ); //no good
        ) {
            if ( rs.next ( ) == false ) {
                return -1.0;
            }
            ret = rs.getDouble ( "charge" );

        } catch (SQLException | IOException e) {
            e.printStackTrace ( );
        }

        return ret;
    }
}
