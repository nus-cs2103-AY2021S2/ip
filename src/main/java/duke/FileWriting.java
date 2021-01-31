package duke;

import java.io.FileWriter;
import java.io.IOException;
/*
Taken from 2103T file access demo
 */

/**
 * A class that is used to write files and catch checked exceptions
 */
public class FileWriting {

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
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