package io.level2.threecopy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ThreeCopy {

    private static void run () {
        BufferedWriter bw1 = null;
        BufferedWriter bw2 = null;
        BufferedWriter bw3 = null;

        try {
            BufferedReader inStream = Files.newBufferedReader ( Paths.get ( "C:\\Users\\alex.racov\\IdeaProjects\\module2\\src\\io\\level1\\Go.dat" ) );
            File file1 = new File ( "src/io/level2/threecopy/file1.txt" );
            if ( !file1.exists ( ) ) {
                file1.createNewFile ( );
            } else {
                bw1 = new BufferedWriter ( new FileWriter ( file1 , false ) );
                bw1.write ( "" );
            }
            File file2 = new File ( "src/io/level2/threecopy/file2.txt" );
            if ( !file2.exists ( ) ) {
                file2.createNewFile ( );
            } else {
                bw2 = new BufferedWriter ( new FileWriter ( file2 , false ) );
                bw2.write ( "" );
            }
            File file3 = new File ( "src/io/level2/threecopy/file3.txt" );
            if ( !file3.exists ( ) ) {
                file3.createNewFile ( );
            } else {
                bw3 = new BufferedWriter ( new FileWriter ( file3 , false ) );
                bw3.write ( "" );
            }


            String line;
            bw1 = new BufferedWriter ( new FileWriter ( file1 , true ) );
            bw2 = new BufferedWriter ( new FileWriter ( file2 , true ) );
            bw3 = new BufferedWriter ( new FileWriter ( file3 , true ) );
            while (( line = inStream.readLine ( ) ) != null) {
                bw1.write ( line + "\n" );
                bw2.write ( line + "\n" );
                bw3.write ( line + "\n" );
            }
            inStream.close ( );

            if ( bw1 != null ) {
                bw1.close ( );
            }
            if ( bw2 != null ) {
                bw2.close ( );
            }
            if ( bw3 != null ) {
                bw3.close ( );
            }
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }

    public static void main ( String[] args ) {
        ThreeCopy.run ( );
    }
}
