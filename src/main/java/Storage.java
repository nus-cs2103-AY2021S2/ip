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

    private static void printFileContents(String filePath) throws FileNotFoundException {
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

    /**
     * gets the home location of the OS using this code
     * @return file path of the home location
     */

    public static String getHome() {
        return System.getProperty("user.home");
    }

    /**
     * saves a default file path that will help to locate the specific path
     * or create a new path id this path does not exist
     * @return file path in the form of a string
     */

    public static String getDefaultFilePath() {
        return getHome() + File.separator + "task.txt";
    }

    /**
     * initialises the process by either
     * printing the contents of the task in the existing documents or
     * creating the specific text file in the user's OS
     * @param filePath the location which the code will look for such that either
     *                 the contents in this file will be printed or
     *                 the code creates the file based on the provided pth
     */

    public static void initialise(String filePath) {
        System.out.println("Your tasks include:");
        String file = getHome() + File.separator + "task.txt";
        try {
            printFileContents(filePath);
        } catch (FileNotFoundException e) {
            new File(file);
            System.out.println("File created: task.txt\n\tFolder: data");
        }
    }
}
