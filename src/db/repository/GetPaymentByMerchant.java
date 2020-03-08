package db.repository;

import db.entity.Merchant;
import db.entity.Payment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetPaymentByMerchant {
    public static ArrayList<Payment> get ( Merchant merchant ) {
        ArrayList<Payment> payments = new ArrayList<> ( );
        if ( merchant == null ) {
            return null;
        }

        try (Connection conn = MyConnection.getConnection ( );
             PreparedStatement ps = createPreparedStatement ( conn , merchant.getMerchantId ( ) );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return null;
            } else {
                do {
                    Payment current = new Payment ( rs.getInt ( "id" ) ,
                            rs.getDate ( "dt" ) ,
                            merchant ,
                            GetCustomer.get ( rs.getInt ( "customerId" ) ) ,
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

    private static PreparedStatement createPreparedStatement ( Connection con , int merchantId ) throws SQLException {
        String sql = "SELECT * FROM payment WHERE merchantId = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , merchantId );
        return ps;
    }
}
