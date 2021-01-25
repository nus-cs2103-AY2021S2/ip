import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReading {

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner scanner = new Scanner(f); // create a Scanner using the File as the source
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }

    public static void printTaskList() {
        try {
            printFileContents("../CS2103_iP/data/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
