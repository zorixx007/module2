package db.repository;

import db.entity.Customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerSQL {


    private static PreparedStatement psGetCustomerByID ( Connection con , int customerId ) throws SQLException {
        String sql = "SELECT * FROM customer where id = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , customerId );
        return ps;
    }

    public static Customer getCustomerByID ( Connection conn , int customerId ) {
        Customer current = null;
        try (PreparedStatement ps = psGetCustomerByID ( conn , customerId );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return null;
            }
            current = new Customer ( rs.getInt ( "id" ) , rs.getString ( "name" ) ,
                    rs.getString ( "address" ) , rs.getString ( "email" ) , rs.getString ( "ccNo" ) ,
                    rs.getString ( "ccType" ) , rs.getDate ( "maturity" ) );
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        return current;
    }
}
