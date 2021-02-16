package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
/*
Taken from 2103T file access demo
 */

/**
 * A class that is used to write files and catch checked exceptions.
 */
public class FileWriting {

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        Path file = Paths.get(filePath);
        Files.write(file, Collections.singleton(textToAdd), StandardCharsets.UTF_8 );
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Writes into file and catches IO excpetion.
     *
     * @param filePath
     * @param string
     */
    protected static void write(String filePath, String string) {
        String file2 = filePath;
        try {
            writeToFile(file2, string);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}