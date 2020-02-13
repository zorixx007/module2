package io.level2.dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadDictionary {
    private static void run () {
        Map<String, String> dict = new HashMap<> ( );
        try {
            BufferedReader csvReader = new BufferedReader ( new FileReader ( "src/io/level2/dictionary/dictionary.csv" ) );
            String row = csvReader.readLine ( ); // skipping first line
            while (( row = csvReader.readLine ( ) ) != null) {
                String[] arrOfStr = row.split ( "," , 2 );
                dict.put ( arrOfStr[0] , arrOfStr[1] );
            }
            csvReader.close ( );
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        dict.forEach ( ( k , v ) -> System.out.println ( k + " : " + v ) );
    }

    public static void main ( String[] args ) {
        ReadDictionary.run ( );
    }

}

