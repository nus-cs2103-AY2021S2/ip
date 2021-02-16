package duke;

import java.io.IOException;

import duke.command.Command;
import duke.parser.Parser;
import duke.exception.DukeException;
import duke.data.DataStorage;
import duke.tasklist.TaskList;
import duke.ui.UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {

    private static DataStorage storage = new DataStorage();
    private static UI ui = new UI();
    private static TaskList tasks = new TaskList();


    @Override
    public void start(Stage stage) {
        Label header = new Label("Welcome to EventLab!"); // Creating a new Label control
        Scene scene = new Scene(header); // Setting the scene to be our Label
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Display welcome message by chat bot
     */
    public String displayStartMsg() {
        return ui.displayWelcomeMessage();
    }

    /**
     * Returns duke's response after processing user input
     */
    public String getResponse(String input) {
        String output = null;
        try {
            Command c = new Command("null");
            c.execute();
            output = Parser.parse(input);
        } catch (DukeException | IOException e) {
            return ui.showError(e.getMessage());
        }
        return output;
    }


}