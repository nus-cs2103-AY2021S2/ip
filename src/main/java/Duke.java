import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;




/**
 * Class Duke is the main class for the execution of Duke chatbot.
 *
 * @version 21 Jan 2021.
 * @author Zhang Peng.
 */
public class Duke {

    /*
        public static void main(String[] args) throws FileNotFoundException {


            System.out.println("--------------------------");
            System.out.println("Hello! I'm Duke");
            System.out.println("What can I do for you?");
            System.out.println("--------------------------");

        }

     */
    public String getResponse(String input) {
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
        Scanner s = null;
        try {
            s = new Scanner(dukeFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert s != null;
        new Storage().loadingFile(arrayList, s);

        return new Parser().makingSenseOfUserCommand(arrayList, path, input);


    }
}





