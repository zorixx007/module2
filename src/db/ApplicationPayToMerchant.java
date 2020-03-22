package db;

import db.repository.CustomerRepository;
import db.repository.DBUtils;
import db.repository.MerchantRepository;
import db.repository.PaymentRepository;
import db.service.MerchantService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/*
Create a method that will send funds to a merchant if the needToSend>minSum. This method
should update the sent and lastSent columns in the Merchant table
*/

public class ApplicationPayToMerchant {
    private static void run () throws IOException, SQLException {
        // create services
        Connection newConnection = new DBUtils ( ).getConnection ();
        MerchantRepository mr = new MerchantRepository ( newConnection );
        CustomerRepository cr = new CustomerRepository ( newConnection );
        PaymentRepository pr = new PaymentRepository ( cr , mr , newConnection );
        MerchantService merchantService = new MerchantService ( mr , pr );
        merchantService.payToMerchant ( );

    }

    public static void main ( String[] args ) {
        try {
            ApplicationPayToMerchant.run ( );
        } catch (IOException e) {
            e.printStackTrace ( );
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }
}
