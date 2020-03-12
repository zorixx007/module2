package db.repository;

import db.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerSQL {


    private static PreparedStatement psGetCustomerByID ( Connection con , int customerId ) throws SQLException {
        String sql = "SELECT * FROM customer where id = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , customerId );
        return ps;
    }

    private static PreparedStatement psGetAllCustomers ( Connection con ) throws SQLException {
        String sql = "SELECT * FROM customer";
        PreparedStatement ps = con.prepareStatement ( sql );
        return ps;
    }

    public static ArrayList<Customer> getAllCustomers ( Connection conn ) {
        ArrayList<Customer> current = new ArrayList<> ( );
        try (PreparedStatement ps = psGetAllCustomers ( conn );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return current;
            } else {
                do {
                    current.add ( new Customer ( rs.getInt ( "id" ) , rs.getString ( "name" ) ,
                            rs.getString ( "address" ) , rs.getString ( "email" ) , rs.getString ( "ccNo" ) ,
                            rs.getString ( "ccType" ) , rs.getDate ( "maturity" ) ) );
                } while (rs.next ( ));
            }
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        return current;
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

    public static PreparedStatement addNewCustomer ( Connection con , Customer newCustomer ) throws SQLException {
        String sql = "INSERT INTO customer (name, address, email, ccNo, ccType, maturity) ";
        sql += " VALUES(?,?,?,?,?,?);";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setString ( 1 , newCustomer.getName ( ) );
        ps.setString ( 2 , newCustomer.getAddress ( ) );
        ps.setString ( 3 , newCustomer.getEmail ( ) );
        ps.setString ( 4 , newCustomer.getCcNo ( ) );
        ps.setString ( 5 , newCustomer.getCcType ( ) );
        ps.setDate ( 6 , newCustomer.getMaturity ( ) );
        return ps;
    }

}
