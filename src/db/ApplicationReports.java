package db;

import db.entity.Customer;
import db.entity.Merchant;
import db.entity.MerchantReport;
import db.entity.Payment;
import db.repository.CustomerRepository;
import db.repository.DBUtils;
import db.repository.MerchantRepository;
import db.repository.PaymentRepository;
import db.service.CustomerService;
import db.service.MerchantService;
import db.service.PaymentService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;

public class ApplicationReports {
    private static void run () {
        // create services
        DBUtils newConnection = new DBUtils ( );
        try {
            final Connection connection = newConnection.getConnection ( );
            MerchantRepository mr = new MerchantRepository ( connection );
            CustomerRepository cr = new CustomerRepository ( connection );
            PaymentRepository pr = new PaymentRepository ( cr , mr , connection );
            MerchantService merchantService = new MerchantService ( mr , pr );
            PaymentService paymentService = new PaymentService ( pr , mr );
            CustomerService customerService = new CustomerService ( cr , pr );



        /*
        Create a specific class and method that will show a total sum payed for a merchant with a given
        id (argument). The report should also contain merchant id, title and lastSent info
        */
            MerchantReport currentReport = merchantService.getSumReportForMerchant ( 2 );
            System.out.println ( currentReport.toString ( ) );
            System.out.println ( );


            /* Create an application to display a list of all merchants sorted alphabetically in descending order */
            LinkedList<Merchant> merchants = mr.getAllMerchantsSorted ( );
            System.out.println ( "print payments sorted by merchant name " );
            merchants.forEach ( item -> System.out.println ( item ) );
            System.out.println ( );


            /* Add a few more payments with updating corresponding columns in Merchant table */
            Payment newp1 = new Payment ( Date.valueOf ( "2020-03-17" ) ,
                    mr.getMerchantByID ( 2 ) ,
                    cr.getCustomerByID ( 3 ) ,
                    "Dell laptop" ,
                    2500 );
            //paymentService.addNewPayment ( newp1 );

            /*Find the most active customer based on the number of order within the passed in time period
            (ie week, month, quarter, year). The resulting Customer object should contain the list of all Payments made. */

            System.out.println ( "print best customer for period" );
            Customer bestCustomerForPeriod = customerService.getBestCustomerForPeriodOfDays ( 3500 );
            System.out.println ( bestCustomerForPeriod );
            System.out.println ( "with Payments list: " );
            System.out.println ( bestCustomerForPeriod.getPayments ( ) );

        } catch (IOException | SQLException e) {
            e.printStackTrace ( );
        }
    }

    public static void main ( String[] args ) {
        ApplicationReports.run ( );
    }
}
