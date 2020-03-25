package db.fileUtils;

import db.entity.Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

public class LoadCustomers {
    public LoadCustomers () {
    }

    public ArrayList<Customer> getCustomersFromFile ( Path filePath ) {
        HashSet<Customer> customersFromFile = new HashSet<> ( );
        //read file -> convert records to Customer objects -> add to hashset
        try (BufferedReader inStream = Files.newBufferedReader ( filePath )) {
            String row = inStream.readLine ( ); // skipping first line
            while (( row = inStream.readLine ( ) ) != null) {
                String[] arrOfStr = row.split ( "," , 6 );
                Date ld = null;
                if ( !arrOfStr[5].isEmpty ( ) ) {
                    ld = Date.valueOf ( LocalDate.parse ( arrOfStr[5] , DateTimeFormatter.ofPattern ( "yyyy/MM/dd" ) ) );
                }
                customersFromFile.add ( new Customer ( 0 , arrOfStr[0] , arrOfStr[1] , arrOfStr[2] ,
                        arrOfStr[3] , arrOfStr[4] , ld ) );
            }
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        return new ArrayList<> ( customersFromFile );
    }
}
