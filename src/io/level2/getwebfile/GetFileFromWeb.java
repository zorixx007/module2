package io.level2.getwebfile;


/*
Google and download the file from https://www.w3schools.com/w3css/4/w3.css. File
should be saved to downloadedFromW3School.css.
*/

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class GetFileFromWeb {
    private static void run () {
        BufferedInputStream inStream = null;
        try {
            inStream = new BufferedInputStream ( new URL ( "https://www.w3schools.com/w3css/4/w3.css" ).openStream ( ) );
            Files.copy ( inStream , Paths.get ( "src/io/level2/getwebfile/downloadedFromW3School.css" ) , StandardCopyOption.REPLACE_EXISTING );
            inStream.close ( );
        } catch (IOException e) {
            e.printStackTrace ( );
        } finally {
//            if ( inStream != null ) {
//                inStream.close ( );
//            }
        }

    }

    public static void main ( String[] args ) {
        GetFileFromWeb.run ( );
    }


}
