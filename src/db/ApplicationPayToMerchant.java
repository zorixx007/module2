package db;

import db.repository.DBUtils;
import db.service.MerchantService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/*
Create a method that will send funds to a merchant if the needToSend>minSum. This method
should update the sent and lastSent columns in the Merchant table
*/

public class ApplicationPayToMerchant {
    private static void run () {
        try (Connection conn = DBUtils.getConnection ( )) {
            MerchantService.payToMerchant ( conn );
        } catch (SQLException | IOException e) {
            e.printStackTrace ( );
        }
    }

    public static void main ( String[] args ) {
        ApplicationPayToMerchant.run ( );
    }
}
