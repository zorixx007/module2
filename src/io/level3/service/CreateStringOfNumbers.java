package io.level3.service;

import static java.lang.StrictMath.abs;

public class CreateStringOfNumbers {
    public static String strOfNumbers ( double input ) {
        String result = "";
        if ( input < 0 ) {
            result = "minus ";
        }
        int inputInt = abs ( (int) input );
        char[] chars = ( String.valueOf ( inputInt ).toCharArray ( ) );
        for (int i = 0; i < chars.length; i++) {
            int num = Character.getNumericValue ( chars[i] );
            result += ConvertNumbersToString.numToStr.get ( num );
            if ( i < chars.length - 1 ) {
                result += "-";
            }
        }
        return result;
    }
}