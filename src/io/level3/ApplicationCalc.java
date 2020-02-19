package io.level3;

import io.level3.service.ConvertStringToMathExpression;
import io.level3.service.CreateStringOfNumbers;
import io.level3.service.SimpleMath;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ApplicationCalc {
    private static void run () {

        Path inputFile = Paths.get ( "src/io/level3/example.txt" );
        try (BufferedReader inStream = Files.newBufferedReader ( inputFile );
             PrintWriter outStream = new PrintWriter ( new FileWriter ( "src/io/level3/output.txt" ) )) {
            String line;
            while (( line = inStream.readLine ( ) ) != null) {
                String s = ConvertStringToMathExpression.strToMath ( line );
                double fullNumberResult = SimpleMath.result ( s );
                String lineToWrite = line + " = " + CreateStringOfNumbers.strOfNumbers ( fullNumberResult ) + " (" + s + " = " + fullNumberResult + ")";
                outStream.println ( lineToWrite );
            }
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }

    public static void main ( String[] args ) {
        ApplicationCalc.run ( );
    }
}
