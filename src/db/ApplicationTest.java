package db;

import db.entity.Customer;
import db.entity.Merchant;
import db.entity.Payment;
import db.repository.CustomerSQL;
import db.repository.DBUtils;
import db.service.CustomerService;
import db.service.PaymentService;
import db.service.MerchantService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class ApplicationTest {

    private static void run () {
        try (Connection conn = DBUtils.getConnection ( )) {

            Merchant current = MerchantService.getMerchantByID ( conn, 2 );
            System.out.println ( current );


            Customer curr = CustomerService.getCustomerByID ( conn,1 );
            System.out.println ( curr );

            ArrayList<Payment> customerPayments = PaymentService.getPaymentsForCustomer (conn, curr );
            System.out.println ( "print payments by customer" );
            customerPayments.forEach ( item -> System.out.println ( item ) );

            ArrayList<Payment> merchantPayments = PaymentService.getPaymentsForMerchant (conn, current );
            System.out.println ( "print payments by merchant" );
            merchantPayments.forEach ( item -> System.out.println ( item ) );
        } catch (SQLException | IOException e) {
            e.printStackTrace ( );
        }

    }

    public static void main ( String[] args ) {
        ApplicationTest.run ( );
    }
}
