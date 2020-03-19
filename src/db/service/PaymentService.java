package db.service;

import db.entity.Payment;
import db.repository.PaymentRepository;

public class PaymentService {

    PaymentRepository paymentRepo;

    public PaymentService ( PaymentRepository paymentRepo ) {

        this.paymentRepo = paymentRepo;
    }

    public void addNewPayment ( Payment newPay ) {
        paymentRepo.addPayment ( newPay );
    }
}
