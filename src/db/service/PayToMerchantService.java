package db.service;

import db.entity.Merchant;
import db.repository.GetAllMerchants;
import db.repository.PayToMerchant;

import java.util.ArrayList;

public class PayToMerchantService {
    public static void pay () {
        ArrayList<Merchant> merchants = GetAllMerchants.get ( );
        for (Merchant current : merchants) {
            double toSend = current.getNeedToSend ( );
            if ( toSend > current.getMinSum ( ) ) {
                System.out.println ( "pay to customer " + current.getName ( ) + " amount " + toSend );
                PayToMerchant.pay ( current , toSend );
            }
        }
    }
}
