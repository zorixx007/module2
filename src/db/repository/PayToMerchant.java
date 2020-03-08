package db.repository;

import db.entity.Merchant;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PayToMerchant {

    public static void pay ( Merchant merchant , double sumToPay ) {
        try (Connection conn = MyConnection.getConnection ( );
        ) {
            conn.setAutoCommit ( false );
            try (PreparedStatement ps = getMerchantNeedToSend ( conn , merchant );
                 ResultSet rs = ps.executeQuery ( )) {
                if ( rs.next ( ) == false ) {
                    return ;
                }
                double currentSum = rs.getDouble ( "needToSend" );
                try (PreparedStatement ps2 = updateMerchant ( conn , merchant , (currentSum-sumToPay) , sumToPay )) {
                    ps2.executeUpdate ( );
                } catch (SQLException e) {
                    e.printStackTrace ( );
                }
                conn.commit ( );
            } catch (Exception e) {
                e.printStackTrace ( );
//                System.out.println ( e.getStackTrace ( ) );
                conn.rollback ( );
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace ( );
        }
    }

    private static PreparedStatement getMerchantNeedToSend ( Connection con , Merchant merchant ) throws SQLException {
        String sql = "SELECT needToSend FROM merchant where id = ?";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setInt ( 1 , merchant.getMerchantId ( ) );
        return ps;
    }

    private static PreparedStatement updateMerchant ( Connection con , Merchant merchant , double currentSum , double sentSum ) throws SQLException {
        String sql = "UPDATE merchant SET needToSend = ?, sent = ?, lastSent = now() WHERE id = ?;";
        PreparedStatement ps = con.prepareStatement ( sql );
        ps.setDouble ( 1 , currentSum );
        ps.setDouble ( 2 , sentSum );
        ps.setInt ( 3 , merchant.getMerchantId ( ) );
        return ps;
    }
}
