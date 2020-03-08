package db.service;

import db.entity.Payment;
import db.bad.GetCharge;
import db.repository.AddPaymentRecord;

public class AddPaymentService {
    public static void addPayment ( Payment newPay ) {
        if ( newPay == null ){
            return;
        } else if ( newPay.getChargePaid ()<0 ){
            return;
        }
        AddPaymentRecord.add (newPay);

//        double currentCharge = GetCharge.get ( newPay.getMerchantId ( ) );
//        newPay.setChargePaid ( newPay.getSumPaid ( ) * currentCharge );
    }
}
