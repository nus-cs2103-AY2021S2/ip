import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Class initialises Duke Bot to take in input from user
 */

public class Duke extends Application {

    private static final Storage storage = new Storage();

    private static final TaskList list = new TaskList();

    private static final Parser parser = new Parser();

    /**
     * Presents saved history via Storage and
     * reads input from users.
     * @param args arguments
     * @throws Exception thrown when Duke cannot handle user input.
     */
    public static void main(String[] args) throws Exception {
        if (storage.isSavedHistory()) {
            storage.initialise(list);
        }
        parser.readInput(list);
        try {
            byeCommand();
        } catch (IOException io) {
            System.out.print("Could not save list!");
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        // Ui ui = new Ui();
        // Label uiWords = new Label(ui.welcomeUser());
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Function to call when we want to end main, to store the TaskList into storage
     *
     * @throws IOException from Storage.
     */
    static void byeCommand() throws IOException {
        storage.saveHistory(list);
    }

}
