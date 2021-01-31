package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
Taken from 2103T file access demo
 */

/**
 * A class that is used to read files and catch checked exceptions
 */
public class FileReading {

    private static String printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String output = "";
        while (s.hasNext()) {
            output += s.nextLine() + "\n";
        }
        return output;
    }

    /**
     * Reads file and generates a string of the contents.
     * Catches FileNotFoundExceptions.
     *
     * @param filePath
     * @return
     */
    protected static String readFile(String filePath) {
        String output = "";
        try {
            output = printFileContents(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } finally {
            return output;
        }
    }

}
