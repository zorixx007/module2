package db;

import db.repository.GetCharge;
import db.repository.ListMerchantReport;
import db.entity.MerchantReport;
import db.service.MerchantReportService;

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
        ListMerchantReport.alphabeticOrder ( );
        System.out.println ( );



    }

    public static void main ( String[] args ) {
        ApplicationReports.run ( );
    }
}
