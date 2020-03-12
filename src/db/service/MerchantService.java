package db.service;

import db.entity.Merchant;
import db.entity.MerchantReport;
import db.entity.Payment;
import db.repository.MerchantSQL;
import db.repository.PaymentSQL;

import java.sql.Connection;
import java.util.ArrayList;

public class MerchantService {
    public static void payToMerchant ( Connection conn ) {
        ArrayList<Merchant> merchants = MerchantSQL.getAllMerchants ( conn );
        for (Merchant current : merchants) {
            double toSend = current.getNeedToSend ( );
            if ( toSend > current.getMinSum ( ) ) {
                System.out.println ( "pay to customer " + current.getName ( ) + " amount " + toSend );
                MerchantSQL.payToMerchant ( conn , current , toSend );
            }
        }
    }


    public static MerchantReport getSumReportForMerchant ( Connection conn , int merchantID ) {
        Merchant merchant = MerchantSQL.getMerchantByID ( conn , merchantID );
        if ( merchant == null ) {
            return null;
        }
        ArrayList<Payment> merchantPayments = PaymentSQL.getPaymentsForMerchant ( conn , merchant );
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
