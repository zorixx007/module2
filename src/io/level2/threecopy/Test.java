package io.level2.threecopy;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Test {
    public static void main ( String[] args ) {
        try {
            Test.createTreeCopiesOfFile ();
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }

    public static void createTreeCopiesOfFile() throws IOException {
        Path filePath = Paths.get("C:\\Users\\alex.racov\\IdeaProjects\\module2\\src\\io\\level1\\Go.dat");
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader (new FileInputStream (filePath.toFile())))
        ) {
            String line;
            reader.mark(10000);
            BufferedWriter writer = null;
            for (int i = 1; i <= 3; i++) {
                Path copyFile = Paths.get("src/io/level2/threecopy/test"+i+".txt");
                try {
                    writer = Files.newBufferedWriter(copyFile, Charset.forName("UTF-8"), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                    }
                    System.out.println("File is saved");
                } finally {
                    writer.close();
                    reader.reset();
                }
            }
        }
    }
}
