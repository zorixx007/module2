package db.repository;

import db.entity.Customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetCustomer {
    public static Customer get ( int customerId ) {
        Customer current = null;
        try (Connection conn = MyConnection.getConnection ( );
             PreparedStatement ps = createPreparedStatement ( conn , customerId );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return null;
            }
            current = new Customer ( rs.getInt ( "id" ) , rs.getString ( "name" ) ,
                    rs.getString ( "address" ) , rs.getString ( "email" ) , rs.getString ( "ccNo" ) ,
                    rs.getString ( "ccType" ) , rs.getDate ( "maturity" ) );
        } catch (SQLException | IOException e) {
            e.printStackTrace ( );
        }
        return current;
    }

    private static PreparedStatement createPreparedStatement ( Connection con , int customerId ) throws SQLException {
        String sql = "SELECT * FROM customer where id = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , customerId );
        return ps;
    }
}
