package db;

import db.entity.Merchant;
import db.entity.MerchantReport;
import db.entity.Payment;
import db.repository.DBUtils;
import db.repository.MerchantSQL;
import db.service.CustomerService;
import db.service.MerchantService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class ApplicationReports {
    private static void run () {
        try (Connection conn = DBUtils.getConnection ( )) {
        /*
        Create a specific class and method that will show a total sum payed for a merchant with a given
        id (argument). The report should also contain merchant id, title and lastSent info
        */
            MerchantReport currentReport = MerchantService.getSumReportForMerchant ( conn , 2 );
            System.out.println ( currentReport.toString ( ) );
            System.out.println ( );


            /* Create an application to display a list of all merchants sorted alphabetically in descending order */
            LinkedList<Merchant> merchants = MerchantSQL.getAllMerchantsSorted ( conn );
            System.out.println ( "print payments by merchant" );
            merchants.forEach ( item -> System.out.println ( item ) );
            System.out.println ( );


            /* Add a few more payments with updating corresponding columns in Merchant table */
            //            Payment newp1 = new Payment ( Date.valueOf ( "2020-03-01" ) ,
            //                    MerchantSQL.getMerchantByID ( conn , 2 ) ,
            //                    CustomerSQL.getCustomerByID ( conn , 1 ) ,
            //                    "Dell laptop" ,
            //                    2500 );
            //            PaymentService.addPayment ( conn , newp1 );

            /*Find the most active customer based on the number of order within the passed in time period
            (ie week, month, quarter, year). The resulting Customer object should contain the list of all Payments made. */
            System.out.println ("print best customer for period" );
            ArrayList<Payment> bestCustomerTransactions = CustomerService.getBestCustomer ( conn , 3500 );
            bestCustomerTransactions.forEach ( item -> System.out.println ( item ) );
            System.out.println ( );


        } catch (SQLException | IOException e) {
            e.printStackTrace ( );
        }
    }

    public static void main ( String[] args ) {
        ApplicationReports.run ( );
    }
}
