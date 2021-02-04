import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Class Duke is the main class for the execution of Duke chatbot.
 *
 * @version 21 Jan 2021.
 * @author Zhang Peng.
 */
public class Duke extends Application  {


    // need to change this start class
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }



    /*
    public static void main(String[] args) throws FileNotFoundException {
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
    */

    
}





