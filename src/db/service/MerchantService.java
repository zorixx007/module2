package db.service;

import db.entity.Merchant;
import db.entity.MerchantReport;
import db.entity.Payment;
import db.fileUtils.LoadMerchants;
import db.repository.MerchantRepository;
import db.repository.PaymentRepository;

import java.nio.file.Path;
import java.util.ArrayList;

public class MerchantService {
    MerchantRepository merchantRepo;
    PaymentRepository paymentRepo;
    LoadMerchants loadMerchantsFileUtils;

    public MerchantService ( MerchantRepository merchantRepo , PaymentRepository paymentRepo , LoadMerchants loadMerchantsFileUtils ) {
        this.merchantRepo = merchantRepo;
        this.paymentRepo = paymentRepo;
        this.loadMerchantsFileUtils = loadMerchantsFileUtils;
    }

    public MerchantService ( MerchantRepository merchantRepo , PaymentRepository paymentRepo ) {
        this.merchantRepo = merchantRepo;
        this.paymentRepo = paymentRepo;
    }

    public void payToMerchant () {
        ArrayList<Merchant> merchants = merchantRepo.getAllMerchants ( );
        for (Merchant current : merchants) {
            double toSend = current.getNeedToSend ( );
            if ( toSend > current.getMinSum ( ) ) {
                System.out.println ( "pay to customer " + current.getName ( ) + " amount " + toSend );
                merchantRepo.payToMerchant ( current , toSend );
            }
        }
    }


    public MerchantReport getSumReportForMerchant ( int merchantID ) {
        Merchant merchant = merchantRepo.getMerchantByID ( merchantID );
        if ( merchant == null ) {
            return null;
        }
        ArrayList<Payment> merchantPayments = paymentRepo.getPaymentsForMerchant ( merchant );
        if ( merchantPayments == null ) {
            return null;
        }
        double sum = merchantPayments.stream ( )
                .mapToDouble ( a -> a.getSumPaid ( ) )
                .sum ( );
        MerchantReport currentReport = new MerchantReport ( merchantID , merchant.getName ( ) , sum , merchant.getLastSent ( ) );
        return currentReport;
    }


    public boolean loadMerchantFromFile ( Path filePath ) {
        //get list of unique merchants from file:
        ArrayList<Merchant> merchantsFromFile = loadMerchantsFileUtils.getMerchantsFromFile ( filePath );

        //get list of merchants from DB
        ArrayList<Merchant> merchantsFromDB = merchantRepo.getAllMerchants ( );

        //remove existed in DB records from merchantsFromFile
        merchantsFromFile.removeAll ( merchantsFromDB );

        //upload merchantsFromFile to db
        return merchantRepo.uploadMerchantListToDB ( merchantsFromFile );
    }

}
