package io.level1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Application {
    private static void run () {
        //        Create an instance of Path and point it to this file using absolute and relative paths
        Path absPath = Paths.get ( "C:\\Users\\alex.racov\\IdeaProjects\\module2\\src\\io\\level1\\Go.dat" );
        Path srcPath = Paths.get ( "io/level1/Go.dat" );

        //        Check if the file really exists
        String s;
        s = Files.exists ( absPath ) ? "file exists" : "file does NOT exist";
        System.out.println ( s );

        //        Copy the content of the file to the new file located on the desktop
        Path destPath = Paths.get ( "C:\\Users\\alex.racov\\Desktop\\newGo.txt" );

        try {
            Files.deleteIfExists ( destPath );
            Files.copy ( absPath , destPath );
        } catch (IOException e) {
            e.printStackTrace ( );
        }

    }

    public static void main ( String[] args ) {
        Application.run ( );
    }
}


