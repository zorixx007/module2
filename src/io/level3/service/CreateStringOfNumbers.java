package io.level3.service;

import static java.lang.StrictMath.abs;

public class CreateStringOfNumbers {
    public static String strOfNumbers ( double input ) {
        String result = "";
        if ( input < 0 ) {
            result = "minus ";
        }

        int inputInt = abs((int) input);
        int length = String.valueOf ( inputInt ).length ( );
        //create reverse array of int
        int[] intArray = new int[length];
        for (int i = 1; i < length + 1; i++) {
            intArray[i - 1] = inputInt % 10;
            inputInt = inputInt / 10;
        }

        //transform reverse array to string

        for (int i = length - 1; i >= 0; i--) {
            result += ConvertNumbersToString.numToStr ( intArray[i] );
            if ( i != 0 ) {
                result += "-";
            }
        }

        return result;
    }
}