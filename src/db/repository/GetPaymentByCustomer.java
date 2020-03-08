package db.repository;

import db.entity.Customer;
import db.entity.Payment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetPaymentByCustomer {

    public static ArrayList<Payment> get ( Customer customer ) {
        ArrayList<Payment> payments = new ArrayList<> ( );
        if ( customer == null ) {
            return null;
        }

        try (Connection conn = MyConnection.getConnection ( );
             PreparedStatement ps = createPreparedStatement ( conn , customer.getCustomerId ( ) );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return null;
            } else {
                do {
                    Payment current = new Payment ( rs.getInt ( "id" ) ,
                            rs.getDate ( "dt" ) ,
                            GetMerchant.get ( rs.getInt ( "merchantId" ) ) ,
                            customer ,
                            rs.getString ( "goods" ) ,
                            rs.getDouble ( "sumPaid" ) ,
                            rs.getDouble ( "chargePaid" ) );
                    payments.add ( current );
                } while (rs.next ( ));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace ( );
        }
        return payments;
    }

    private static PreparedStatement createPreparedStatement ( Connection con , int customerId ) throws SQLException {
        String sql = "SELECT * FROM payment WHERE customerId = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , customerId );
        return ps;
    }
}
