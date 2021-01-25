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
        int taskNumber = 1;
        while (scanner.hasNext()) {
            System.out.println(taskNumber + ". "+ scanner.nextLine());
            taskNumber += 1;
        }
    }

    public static void printTaskList() {
        try {
            printFileContents("../CS2103_iP/data/duke.txt");
            System.out.println("---------------------------------------------");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
