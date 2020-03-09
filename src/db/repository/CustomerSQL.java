package db.repository;

import db.entity.Customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerSQL {


    public static PreparedStatement getCustomerByID ( Connection con , int customerId ) throws SQLException {
        String sql = "SELECT * FROM customer where id = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , customerId );
        return ps;
    }
}
