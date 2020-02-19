package io.level3.service;

public class ConvertStringToNumbers {
    public static String strToNum ( String input ) {
        String current = "";
        String[] partNumber = input.split ( "-" );
        for (String s : partNumber) {
            switch (s) {
                case "one":
                    current += "1";
                    break;
                case "two":
                    current += "2";
                    break;
                case "three":
                    current += "3";
                    break;
                case "four":
                    current += "4";
                    break;
                case "five":
                    current += "5";
                    break;
                case "six":
                    current += "6";
                    break;
                case "seven":
                    current += "7";
                    break;
                case "eight":
                    current += "8";
                    break;
                case "nine":
                    current += "nine";
                    break;
            }
        }
        return current;
    }
}
