package db.service;

import db.entity.Merchant;
import db.repository.MerchantSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PayToMerchantService {


    public static void pay ( Connection conn ) {

        ArrayList<Merchant> merchants = MerchantService.getAllMerchants ( conn );
        for (Merchant current : merchants) {
            double toSend = current.getNeedToSend ( );
            if ( toSend > current.getMinSum ( ) ) {
                System.out.println ( "pay to customer " + current.getName ( ) + " amount " + toSend );
                try {
                    conn.setAutoCommit ( false );
                    payToMerchant ( conn , current , toSend );
                } catch (SQLException e) {
                    e.printStackTrace ( );
                }

            }
        }
    }

    private static void payToMerchant ( Connection conn , Merchant merchant , double sumToPay ) throws SQLException {

        try (PreparedStatement ps = MerchantSQL.getNeedToSend ( conn , merchant );
             ResultSet rs = ps.executeQuery ( )) {
            if ( rs.next ( ) == false ) {
                return;
            }
            double currentSum = rs.getDouble ( "needToSend" );
            try (PreparedStatement ps2 = MerchantSQL.updatePayToMerchant ( conn , merchant , ( currentSum - sumToPay ) , sumToPay )) {
                ps2.executeUpdate ( );
            } catch (SQLException e) {
                e.printStackTrace ( );
            }
            conn.commit ( );
        } catch (Exception e) {
            e.printStackTrace ( );
            conn.rollback ( );
        }

    }


}
