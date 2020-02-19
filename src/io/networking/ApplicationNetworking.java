package io.networking;

import io.level1.Application;
import io.level2.getwebfile.GetFileFromWeb;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ApplicationNetworking {

    private static void run () {
        try (BufferedInputStream inStream = new BufferedInputStream ( new URL ( "http://www.ukrstat.gov.ua/express/expr2019/03/38.pdf" ).openStream ( ) );){
            Files.copy ( inStream , Paths.get ( "src/io/networking/test.pdf" ) , StandardCopyOption.REPLACE_EXISTING );
        } catch (IOException e) {
            e.printStackTrace ( );
        }

    }

    public static void main ( String[] args ) {
        ApplicationNetworking.run ( );
    }

}
