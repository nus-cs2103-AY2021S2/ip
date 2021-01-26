package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
Taken from 2103T file access demo
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

    protected static String readFile(String args) {
        String output = "";
        try {
            output = printFileContents(args);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } finally{
            return output;
        }
    }

}
