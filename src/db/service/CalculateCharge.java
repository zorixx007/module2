package db.service;

import db.entity.Payment;
import db.repository.GetCharge;

public class CalculateCharge {
    public static void calculate ( Payment newPay ) {
        double currentCharge = GetCharge.get ( newPay.getMerchantId ( ) );
        newPay.setChargePaid ( newPay.getSumPaid ( ) * currentCharge );
    }
}
