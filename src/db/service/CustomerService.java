package db.service;

import db.entity.Customer;
import db.fileUtils.LoadCustomers;
import db.repository.CustomerRepository;
import db.repository.PaymentRepository;

import java.nio.file.Path;
import java.util.ArrayList;

public class CustomerService {

    CustomerRepository customerRepo;
    PaymentRepository paymentRepo;
    LoadCustomers loadCustomersFileUtils;

    public CustomerService ( CustomerRepository customerRepo , PaymentRepository paymentRepo , LoadCustomers newLoad ) {
        this.customerRepo = customerRepo;
        this.paymentRepo = paymentRepo;
        this.loadCustomersFileUtils = newLoad;
    }

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

    public boolean loadCustomersFromFile ( Path filePath ) {
        //get list of unique customers from file:
        ArrayList<Customer> customersFromFile = loadCustomersFileUtils.getCustomersFromFile ( filePath );

        //get list of customers from DB
        ArrayList<Customer> customersFromDB = customerRepo.getAllCustomers ( );

        //remove existed in DB records from customersFromFile
        customersFromFile.removeAll ( customersFromDB );

        //upload customersFromFile to db
        return customerRepo.uploadCustomerListToDB ( customersFromFile );
    }
}
