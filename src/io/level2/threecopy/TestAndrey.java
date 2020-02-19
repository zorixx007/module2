package io.level2.threecopy;

import java.io.*;
import java.net.URL;

public class TestAndrey {


        public static void run () throws IOException

        {
            URL url = new URL ( "https://www.flyrnai.org/compleat/ExampleFiles.jsp" );
            try (BufferedReader inputStream = new BufferedReader ( new InputStreamReader ( url.openStream ( ) ) );
                 PrintWriter outputStream = new PrintWriter ( new FileWriter ( "src/io/level2/threecopy/testA1.txt" ) );
                 PrintWriter outputStream2 = new PrintWriter ( new FileWriter ( "src/io/level2/threecopy/testA2.txt" ) );
                 PrintWriter outputStream3 = new PrintWriter ( new FileWriter ( "src/io/level2/threecopy/testA3.txt" ) )) {
                String line;
                while (( line = inputStream.readLine ( ) ) != null) {
                    outputStream.println ( line );
                    outputStream2.println ( line );
                    outputStream3.println ( line );
                }
            }
        }


    public static void main ( String[] args ) {
        try {
            TestAndrey.run();
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }


}