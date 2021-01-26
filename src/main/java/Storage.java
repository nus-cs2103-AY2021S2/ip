import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public Storage(){}

    static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void printFileContents(String filePath, ArrayList<Task> lst) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {

            System.out.println(s.nextLine());
        }
    }

    static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static String getHome() {
        return System.getProperty("user.home");
    }

    public static String defaultFilePath() {
        return getHome() + File.separator + "task.txt";
    }

    public static void printContent(String filePath, ArrayList<Task> lst) {
        System.out.println("Your tasks include:");
        String file = getHome() + File.separator + "task.txt";
        try {
            printFileContents(filePath, lst);
        } catch (FileNotFoundException e) {
            new File(file);
            System.out.println("File created: task.txt\n\tFolder: data");
        }
    }
}
