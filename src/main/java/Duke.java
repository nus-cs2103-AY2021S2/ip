import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class Duke is the main class for the execution of Duke chatbot.
 *
 * @version 21 Jan 2021.
 * @author Zhang Peng.
 */
public class Duke {

    /**
     * This is the main method for the Duke class .
     * @param args Unused.
     * @return Nothing.
     */

    public static void main(String[] args) throws FileNotFoundException {
        String input;
        ArrayList<Task> arrayList = new ArrayList<>();

        String path = "duke.txt";
        File dukeFile = new File("duke.txt"); // doest matter if the path exit
        boolean result;

        try {
            result = dukeFile.createNewFile();
            if (result) {
               System.out.println(".txt file created!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //read from the file content into the arrayList at the start.
        Scanner s = new Scanner(dukeFile);
        new Storage().loadingFile(arrayList, s);

        System.out.println("--------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------");

        new Ui().interactWithUser(arrayList, path);
    }
}





