package db.service;

import db.entity.Payment;
import db.repository.PaymentSQL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerService {

    public static ArrayList<Payment> getBestCustomer ( Connection conn , int period ) {
        // get list of payments
        ArrayList<Payment> allPaymentsForPeriod = PaymentSQL.getPaymentsByPeriod ( conn , period );

        //create list of customersID based on payment list
        ArrayList<Integer> customerID = new ArrayList<> ( );
        allPaymentsForPeriod.forEach ( item -> customerID.add ( item.getCustomer ( ).getCustomerId ( ) ) );

        //create map of occurrence
        Map<Integer, Long> occurrences = customerID.stream ( ).collect ( Collectors.groupingBy ( w -> w , Collectors.counting ( ) ) );

        //find max value key
        int customerKey = Collections.max ( occurrences.entrySet ( ) , Map.Entry.comparingByValue ( ) ).getKey ( );

        //create new list of payment with only our customer
//        todo: how to refactor with lambda ????
        ArrayList<Payment> bestCustomerTransactions = new ArrayList<> ( );
        for (Payment item : allPaymentsForPeriod) {
            if ( item.getCustomer ( ).getCustomerId ( ) == customerKey ) {
                bestCustomerTransactions.add ( item );
            }
        }

        return bestCustomerTransactions;
    }
}
