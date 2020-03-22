package db.service;

import db.entity.Customer;
import db.repository.CustomerRepository;
import db.repository.PaymentRepository;

import java.util.ArrayList;

public class CustomerService {

    CustomerRepository customerRepo;
    PaymentRepository paymentRepo;

    public CustomerService ( CustomerRepository customerRepo , PaymentRepository paymentRepo ) {
        this.customerRepo = customerRepo;
        this.paymentRepo = paymentRepo;
    }

    public Customer getBestCustomerForPeriod ( int period ) {
        Customer bestCustomer = null;

        // get list of customers
        ArrayList<Customer> customersForPeriod = paymentRepo.getCustomersWithPaymentsByPeriod ( period );
        if ( customersForPeriod.size ( ) == 0 ) {
            return bestCustomer;
        }

        // sort list of customers
        customersForPeriod.sort ( Customer.comparatorCustomerByPaymentSize );
        bestCustomer = customersForPeriod.get ( 0 );
        return bestCustomer;
    }


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
}
