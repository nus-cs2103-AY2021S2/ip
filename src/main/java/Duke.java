import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class Duke extends Application {

    static Storage storage = new Storage(); 

    static TaskList list = new TaskList();

    static Parser parser = new Parser();

    public static void main(String[] args) throws Exception {
        if(storage.isSavedHistory()) {
            storage.initialise(list);
        }
        //Parser
        parser.readInput(list);
        
        //Storage
        try {
            byeCommand();
        } catch (IOException io) {
            System.out.print("Could not save list!");
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Ui ui = new Ui();
//        Label uiWords = new Label(ui.welcomeUser());
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Function to call when we want to end main, to store the TaskList into storage
     * 
     * @throws IOException
     */
    static void byeCommand() throws IOException {
        storage.saveHistory(list);
    }

}



