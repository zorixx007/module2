package db.fileUtils;

import db.entity.Customer;
import db.entity.Merchant;
import db.entity.Payment;
import db.repository.CustomerRepository;
import db.repository.MerchantRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LoadPayments {
    MerchantRepository merchantRepo;
    CustomerRepository customerRepo;

    public LoadPayments ( MerchantRepository merchantRepo , CustomerRepository customerRepo ) {
        this.merchantRepo = merchantRepo;
        this.customerRepo = customerRepo;
    }

    public ArrayList<Payment> getPaymentsFromFile ( Path filePath ) {
        ArrayList<Payment> paymentsFromFile = new ArrayList<> ( );
        //read file -> convert records to Payment objects
        try (BufferedReader inStream = Files.newBufferedReader ( filePath )) {
            String row = inStream.readLine ( ); // skipping first line
            while (( row = inStream.readLine ( ) ) != null) {
                String[] arrOfStr = row.split ( "," , 5 );
                Date ld = null;
                if ( !arrOfStr[0].isEmpty ( ) ) {
                    ld = Date.valueOf ( LocalDate.parse ( arrOfStr[0] , DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss" ) ) );
                }
                Customer currentCustomer = customerRepo.getCustomerByName ( arrOfStr[1] );
                Merchant currentMerchant = merchantRepo.getMerchantByName ( arrOfStr[2] );
                double sum = Double.parseDouble ( arrOfStr[4] );
                if ( sum != 0 && currentCustomer != null && currentMerchant != null ) {
                    paymentsFromFile.add ( new Payment ( ld , currentMerchant , currentCustomer , arrOfStr[3] , sum ) );
                }
            }
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        return paymentsFromFile;
    }
}
