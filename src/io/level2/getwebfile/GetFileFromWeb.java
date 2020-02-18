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
        try (BufferedInputStream inStream = new BufferedInputStream ( new URL ( "https://www.w3schools.com/w3css/4/w3.css" ).openStream ( ) );){
            Files.copy ( inStream , Paths.get ( "src/io/level2/getwebfile/downloadedFromW3School.css" ) , StandardCopyOption.REPLACE_EXISTING );
        } catch (IOException e) {
            e.printStackTrace ( );
        }

    }

    public static void main ( String[] args ) {
        GetFileFromWeb.run ( );
    }


}
