import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Duke is the main class for the execution of Duke chatbot.
 *@author Zhang Peng.
 * @version 21 Jan 2021.
 */
public class Duke {

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
        assert input != null;
        return new Parser().makingSenseOfUserCommand(arrayList, path, input);


    }
}





