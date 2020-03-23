package db;

import db.fileUtils.LoadCustomers;
import db.fileUtils.LoadMerchants;
import db.repository.CustomerRepository;
import db.repository.DBUtils;
import db.repository.MerchantRepository;
import db.repository.PaymentRepository;
import db.service.CustomerService;
import db.service.MerchantService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

public class ApplicationLoadFromFile {
    private static void run () {
        try {
            //declare connections and path to files
            final Path customerFile = Paths.get ( "src/db/resources/Customers_2020_03_08.csv" );
            final Path merchantFile = Paths.get ( "src/db/resources/Merchants_2020_03_08.csv" );

            final Connection newConnection = new DBUtils ( ).getConnection ( );

            //create instances
            LoadCustomers loadCustomers = new LoadCustomers ( );
            LoadMerchants loadMerchants = new LoadMerchants ( );
            MerchantRepository mr = new MerchantRepository ( newConnection );
            CustomerRepository cr = new CustomerRepository ( newConnection );
            PaymentRepository pr = new PaymentRepository ( cr , mr , newConnection );
            MerchantService merchantService = new MerchantService ( mr , pr , loadMerchants );
            CustomerService customerService = new CustomerService ( cr , pr , loadCustomers );

            //load Customers
            boolean isLoadCustomer = customerService.loadCustomersFromFile ( customerFile );
            System.out.println ( isLoadCustomer ? "Load Customers from file to DataBase was successful!" : "Load Customers from file to DataBase failed!" );

            //load Merchants
            boolean isLoadMerchant = merchantService.loadMerchantFromFile ( merchantFile );
            System.out.println ( isLoadMerchant ? "Load Merchants from file to DataBase was successful!" : "Load Merchants from file to DataBase failed!" );


        } catch (IOException | SQLException e) {
            e.printStackTrace ( );
        }
    }

    public static void main ( String[] args ) {
        ApplicationLoadFromFile.run ( );
    }
}


