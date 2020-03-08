package db;

import db.entity.Customer;
import db.entity.Merchant;
import db.entity.Payment;
import db.repository.GetCustomer;
import db.repository.GetMerchant;
import db.repository.GetPaymentByCustomer;
import db.repository.GetPaymentByMerchant;

import java.util.ArrayList;


public class ApplicationTest {

    private static void run (){
        Merchant current = GetMerchant.get ( 2 );
        System.out.println ( current );


        Customer curr = GetCustomer.get ( 3 );
        System.out.println ( curr );

        ArrayList<Payment> customerPayments = GetPaymentByCustomer.get ( curr );
        System.out.println ("print payments by customer" );
        customerPayments.forEach ( item -> System.out.println ( item ) );

        ArrayList<Payment> merchantPayments = GetPaymentByMerchant.get ( current );
        System.out.println ("print payments by merchant" );
        merchantPayments.forEach ( item -> System.out.println ( item ) );


    }
    public static void main ( String[] args ) {
        ApplicationTest.run ();
    }
}
