package db;

import db.service.PayToMerchantService;

/*
Create a method that will send funds to a merchant if the needToSend>minSum. This method
should update the sent and lastSent columns in the Merchant table
*/

public class ApplicationPayToMerchant {
    private static void run(){
        PayToMerchantService.pay ();
    }

    public static void main ( String[] args ) {
        ApplicationPayToMerchant.run();
    }
}
