package db;

import db.entity.Customer;
import db.repository.CustomerRepository;
import db.repository.DBUtils;
import db.repository.MerchantRepository;
import db.repository.PaymentRepository;
import db.service.CustomerService;
import db.service.MerchantService;
import db.service.PaymentService;


public class ApplicationTest {


    private static void runTest () {
        // create services
        DBUtils newConnection = new DBUtils ( );
        MerchantRepository mr = new MerchantRepository ( newConnection );
        CustomerRepository cr = new CustomerRepository ( newConnection );
        PaymentRepository pr = new PaymentRepository ( cr , mr , newConnection );
        MerchantService merchantService = new MerchantService ( mr , pr );
        PaymentService paymentService = new PaymentService ( pr , mr );
        CustomerService customerService = new CustomerService ( cr , pr );

        Customer test = cr.getCustomerByID ( 1 );
        System.out.println ( test );
    }

    public static void main ( String[] args ) {
        ApplicationTest.runTest ( );
    }
}
