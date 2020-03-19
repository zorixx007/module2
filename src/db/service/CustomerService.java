//package db.service;
//
//import db.entity.Customer;
//import db.entity.Payment;
//import db.repository.CustomerRepository;
//import db.repository.PaymentRepository;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class CustomerService {
//
//    CustomerRepository customerRepo;
//
//    public CustomerService ( CustomerRepository customerRepo ) {
//        this.customerRepo = customerRepo;
//    }
//
//    // todo: refactor customer
//    public static ArrayList<Payment> getBestCustomer ( Connection conn , int period ) {
//        //create an empty list of best customer payments
//        ArrayList<Payment> bestCustomerTransactions = new ArrayList<> ( );
//
//        // get list of payments
//        ArrayList<Payment> allPaymentsForPeriod = PaymentRepository.getPaymentsByPeriod ( conn , period );
//        if ( allPaymentsForPeriod.size ( ) == 0 ) {
//            return bestCustomerTransactions;
//        }
//        //create list of customersID based on payment list
//        ArrayList<Integer> customerID = new ArrayList<> ( );
//        allPaymentsForPeriod.forEach ( item -> customerID.add ( item.getCustomer ( ).getCustomerId ( ) ) );
//        if ( customerID.size ( ) == 0 ) {
//            return bestCustomerTransactions;
//        }
//        //create map of occurrence
//        Map<Integer, Long> occurrences = customerID.stream ( ).collect ( Collectors.groupingBy ( w -> w , Collectors.counting ( ) ) );
//
//        //find max value key
//        int customerKey = Collections.max ( occurrences.entrySet ( ) , Map.Entry.comparingByValue ( ) ).getKey ( );
//
//        //fill list of payment with only our customer
//        //        todo: how to refactor with lambda ????
//        for (Payment item : allPaymentsForPeriod) {
//            if ( item.getCustomer ( ).getCustomerId ( ) == customerKey ) {
//                bestCustomerTransactions.add ( item );
//            }
//        }
//        return bestCustomerTransactions;
//    }
//
//    public static boolean uploadCustomersFromFile ( Connection conn , Path filePath ) {
//        //create an empty HashMap of new Customers loaded from file
//
//        //todo with hashset with override equals with name
//        HashMap<String, Customer> newCustomers = new HashMap<> ( );
//
//        //read file -> convert records to Customer objects -> add to hashmap with lowcase name as key
//        try (BufferedReader inStream = Files.newBufferedReader ( filePath )) {
//            String row = inStream.readLine ( ); // skipping first line
//            while (( row = inStream.readLine ( ) ) != null) {
//                String[] arrOfStr = row.split ( "," , 6 );
//                Date ld = null;
//                if ( !arrOfStr[5].isEmpty ( ) ) {
//                    ld = Date.valueOf ( LocalDate.parse ( arrOfStr[5] , DateTimeFormatter.ofPattern ( "yyyy/MM/dd" ) ) );
//                }
//                newCustomers.put ( arrOfStr[0].toLowerCase ( ) , new Customer ( 0 , arrOfStr[0] , arrOfStr[1] , arrOfStr[2] ,
//                        arrOfStr[3] , arrOfStr[4] , ld ) );
//            }
//        } catch (IOException e) {
//            e.printStackTrace ( );
//            return false;
//        }
//
//        //check if newCustomers is not null
//        if ( newCustomers.size ( ) == 0 ) {
//            return true;
//        }
//
//        //get list of full customers from db
//        ArrayList<Customer> AllCustomersList = CustomerRepository.getAllCustomers ( conn );
//
//        //transfer AllCustomersList to map
//        HashMap<String, Customer> allCustomers = new HashMap<> ( );
//        AllCustomersList.forEach ( item -> allCustomers.put ( item.getName ( ).toLowerCase ( ) , item ) );
//
//        //find unique keys in newCustomers MAP by union keys and remove allCustomers keys
//        //todo: talk about speed of these operations
//        HashSet<String> unionKeys = new HashSet<> ( allCustomers.keySet ( ) );
//        unionKeys.addAll ( newCustomers.keySet ( ) );
//        unionKeys.removeAll ( allCustomers.keySet ( ) );
//
//        //debug - output list of customers to add
//        unionKeys.forEach ( item -> System.out.println ( newCustomers.get ( item ) ) );
//
//        // add required customers into db
//        unionKeys.forEach ( item -> {
//            try {
//                CustomerRepository.addNewCustomer ( conn , newCustomers.get ( item ) );
//            } catch (SQLException e) {
//                e.printStackTrace ( );
//            }
//        } );
//        return true;
//    }
//}
