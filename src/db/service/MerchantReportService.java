package db.service;

import db.entity.Merchant;
import db.entity.MerchantReport;
import db.entity.Payment;
import db.repository.GetMerchant;
import db.repository.GetPaymentByMerchant;

import java.util.ArrayList;

public class MerchantReportService {

    public static MerchantReport get ( int merchantID ) {

        Merchant merchant = GetMerchant.get ( merchantID );
        if ( merchant == null ) {
            return null;
        }
        ArrayList<Payment> merchantPayments = GetPaymentByMerchant.get ( merchant );
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
