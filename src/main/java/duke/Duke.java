package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.image.Image;


public class Duke {
    private Storage storage;
    private TaskManager taskManager;
    private Ui ui;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/image1.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/image2.jpg"));

    public Duke() {
        ui = new Ui();
        storage = new Storage("./src/main/data/Data.txt");
        taskManager = new TaskManager();
        try {
            ArrayList<String> oldData = storage.load();
            ArrayList<String> parsedData = Parser.parseToStart(oldData);
            taskManager.upload(parsedData);
        } catch (FileNotFoundException e) {
            ui.showError("Storage file not found!");
        }
    }

    /**
     * Handles the user interaction and execution of commands.
     */
    public String run(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(taskManager, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        } catch (IOException e) {
            return ui.showError("Internal error! Storage file not in the correct format.");
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return run(input);
    }

}
