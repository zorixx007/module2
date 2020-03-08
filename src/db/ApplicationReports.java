package db;

import db.bad.ListMerchantReport;
import db.entity.Customer;
import db.entity.Merchant;
import db.entity.MerchantReport;
import db.entity.Payment;
import db.repository.GetAllMerchantsSorted;
import db.repository.GetCustomer;
import db.repository.GetMerchant;
import db.repository.GetPaymentByMerchant;
import db.service.AddPaymentService;
import db.service.MerchantReportService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;

public class ApplicationReports {
    private static void run () {

        /*
        Create a specific class and method that will show a total sum payed for a merchant with a given
        id (argument). The report should also contain merchant id, title and lastSent info
        */
        MerchantReport currentReport = MerchantReportService.get ( 2 );
        System.out.println ( currentReport.toString ( ) );
        System.out.println ( );


        /* Create an application to display a list of all merchants sorted alphabetically in descending order */
        LinkedList<Merchant> merchants = GetAllMerchantsSorted.get (  );
        System.out.println ("print payments by merchant" );
        merchants.forEach ( item -> System.out.println ( item ) );


        /* Add a few more payments with updating corresponding columns in Merchant table */
//        Payment newp1 = new Payment ( Date.valueOf ( "2018-03-01"), GetMerchant.get ( 2 ), GetCustomer.get(2), "Lenovo laptop", 2300) ;
//        AddPaymentService.addPayment ( newp1 );




    }

    public static void main ( String[] args ) {
        ApplicationReports.run ( );
    }
}
