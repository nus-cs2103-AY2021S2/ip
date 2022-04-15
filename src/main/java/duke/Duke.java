package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import duke.command.Command;
import javafx.scene.image.Image;


public class Duke {
    private Storage storage;
    private TaskManager taskManager;
    private Ui ui;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/image1.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/image2.jpg"));

    /**
     * Constructor to create a duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("Data.txt");
        taskManager = new TaskManager();
        try {
            ArrayList<String> oldData = storage.load();
            ArrayList<String> parsedData = Parser.parseToStart(oldData);
            taskManager.upload(parsedData);
        } catch (FileNotFoundException e) {
            ui.showError("Storage file not found!");
        } catch (IOException e) {
            ui.showError("Storage file in incorrect format!");
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
     * Retrieves response for user input.
     *
     * @param input user input to get response for.
     * @return String response to user input.
     */
    public String getResponse(String input) {
        return run(input);
    }

}
