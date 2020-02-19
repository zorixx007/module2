package io.level3.service;

public class ConvertStringToMathExpression {
    //    Convert String to Math expression

    public static String strToMath ( String input ) {
        String current = "";
        String[] parts = input.split ( " " );
        for (String s : parts) {
            switch (s) {
                case "curly-brace-open":
                    current += "(";
                    break;
                case "curly-brace-close":
                    current += ")";
                    break;
                case "plus":
                    current += "+";
                    break;
                case "minus":
                    current += "-";
                    break;
                case "multiply":
                    current += "*";
                    break;
                case "multiply-by":
                    current += "*";
                    break;
                case "divide":
                    current += "/";
                    break;
                case "divide-by":
                    current += "/";
                    break;
                default:
                    current += ConvertStringToNumbers.strToNum ( s );
            }
        }
        return current;
    }
}

