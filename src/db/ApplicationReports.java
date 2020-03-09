package db;

import db.entity.Merchant;
import db.entity.MerchantReport;
import db.entity.Payment;
import db.repository.DBUtils;
import db.service.CustomerService;
import db.service.MerchantReportService;
import db.service.MerchantService;
import db.service.PaymentService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;

public class ApplicationReports {
    private static void run () {
        try (Connection conn = DBUtils.getConnection ( )) {
        /*
        Create a specific class and method that will show a total sum payed for a merchant with a given
        id (argument). The report should also contain merchant id, title and lastSent info
        */
            MerchantReport currentReport = MerchantReportService.getSumReportForMerchant ( conn , 2 );
            System.out.println ( currentReport.toString ( ) );
            System.out.println ( );


            /* Create an application to display a list of all merchants sorted alphabetically in descending order */
            LinkedList<Merchant> merchants = MerchantService.getAllMerchantsSorted ( conn );
            System.out.println ( "print payments by merchant" );
            merchants.forEach ( item -> System.out.println ( item ) );


            /* Add a few more payments with updating corresponding columns in Merchant table */
            Payment newp1 = new Payment ( Date.valueOf ( "2018-03-01" ) ,
                    MerchantService.getMerchantByID ( conn , 2 ) ,
                    CustomerService.getCustomerByID ( conn , 1 ) ,
                    "Dell laptop" ,
                    2500 );
            PaymentService.addPayment ( conn , newp1 );

        } catch (SQLException | IOException e) {
            e.printStackTrace ( );
        }
    }

    public static void main ( String[] args ) {
        ApplicationReports.run ( );
    }
}
