package db.service;

import db.entity.Customer;
import db.repository.CustomerSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerService {

    public static Customer getCustomerByID ( Connection conn , int customerId ) {
        Customer current = null;
        try (PreparedStatement ps = CustomerSQL.getCustomerByID ( conn , customerId );
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
