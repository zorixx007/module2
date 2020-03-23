package db.fileUtils;

import db.entity.Merchant;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

public class LoadMerchants {
    public LoadMerchants () {
    }


    public ArrayList<Merchant> getMerchantsFromFile ( Path filePath ) {
        HashSet<Merchant> merchantsFromFile = new HashSet<> ( );
        //read file -> convert records to Merchant objects -> add to hashset
        try (BufferedReader inStream = Files.newBufferedReader ( filePath )) {
            String row = inStream.readLine ( ); // skipping first line
            while (( row = inStream.readLine ( ) ) != null) {
                String[] arrOfStr = row.split ( "," , 10 );
                LocalDate ld = null;
                if ( !arrOfStr[9].isEmpty ( ) && !arrOfStr[9].equals ( "null" ) ) {
                    ld = LocalDate.parse ( arrOfStr[9] , DateTimeFormatter.ofPattern ( "yyyy/MM/dd" ) );
                }
                merchantsFromFile.add ( new Merchant ( 0 ,  //merchantId
                        arrOfStr[0] , //name
                        arrOfStr[1] , //bankName
                        arrOfStr[2] , //swift
                        arrOfStr[3] , // account
                        Double.parseDouble ( arrOfStr[4] ) , //charge
                        Integer.parseInt ( arrOfStr[5] ) , // period
                        Double.parseDouble ( arrOfStr[6] ) , //minSum
                        Double.parseDouble ( arrOfStr[7] ) , //needToSend
                        Double.parseDouble ( arrOfStr[8] ) , //sent
                        ld ) );  //lastSent
            }
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        return new ArrayList<> ( merchantsFromFile );
    }
}
