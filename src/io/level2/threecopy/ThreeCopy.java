package io.level2.threecopy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ThreeCopy {
    private static void run () {
        Path file1 = Paths.get ( "src/io/level2/threecopy/file1.txt" );
        Path file2 = Paths.get ( "src/io/level2/threecopy/file2.txt" );
        Path file3 = Paths.get ( "src/io/level2/threecopy/file3.txt" );
        try (BufferedReader inStream = Files.newBufferedReader ( Paths.get ( "C:\\Users\\alex.racov\\IdeaProjects\\module2\\src\\io\\level1\\Go.dat" ) );
             BufferedWriter bw1 = new BufferedWriter ( new FileWriter ( file1.toFile ( ) ) );
             BufferedWriter bw2 = new BufferedWriter ( new FileWriter ( file2.toFile ( ) ) );
             BufferedWriter bw3 = new BufferedWriter ( new FileWriter ( file3.toFile ( )  ) );
        ) {
            String line;
            while (( line = inStream.readLine ( ) ) != null) {
                bw1.write ( line + "\n" );
                bw2.write ( line + "\n" );
                bw3.write ( line + "\n" );
            }
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }

    public static void main ( String[] args ) {
        ThreeCopy.run ( );
    }
}
