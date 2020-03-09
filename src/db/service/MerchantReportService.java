package db.service;

import db.entity.Merchant;
import db.entity.MerchantReport;
import db.entity.Payment;

import java.sql.Connection;
import java.util.ArrayList;

public class MerchantReportService {


    public static MerchantReport getSumReportForMerchant ( Connection conn , int merchantID ) {

        Merchant merchant = MerchantService.getMerchantByID ( conn , merchantID );
        if ( merchant == null ) {
            return null;
        }
        ArrayList<Payment> merchantPayments = PaymentService.getPaymentsForMerchant ( conn , merchant );
        if ( merchantPayments == null ) {
            return null;
        }

        double sum = merchantPayments.stream ( )
                .mapToDouble ( a -> a.getSumPaid ( ) )
                .sum ( );

        MerchantReport currentReport = new MerchantReport ( merchantID , merchant.getName ( ) , sum , merchant.getLastSent ( ) );
        return currentReport;
    }
}
