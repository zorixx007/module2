package io.level3.service;

import java.util.HashMap;
import java.util.Map;

public class ConvertStringToNumbers {
    private static Map<String, String> strToNum = new HashMap<String, String> ( ) {{
        put ( "one" , "1" );
        put ( "two" , "2" );
        put ( "three" , "3" );
        put ( "four" , "4" );
        put ( "five" , "5" );
        put ( "six" , "6" );
        put ( "seven" , "7" );
        put ( "eight" , "8" );
        put ( "nine" , "9" );
    }};

    public static String strToNum ( String input ) {
        String current = "";
        String[] partNumber = input.split ( "-" );
        for (String s : partNumber) {
            current += strToNum.get ( s );
        }
        return current;
    }
}
