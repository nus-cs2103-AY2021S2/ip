package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Platform;

/**
 * Driver program for Duke.
 */
public class Duke {

    private static final Path storagePath = Paths.get(".", "data", "duke.txt");
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for Duke
     */
    public Duke() {
        this.storage = new Storage(Duke.storagePath);
        this.taskList = new TaskList(this.storage.read());
        if (taskList.size() == 0) {
            this.taskList = new TaskList();
        }
        Parser.populateCommands();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            String response = Parser.parse(input, this.taskList, this.storage);
            assert response != null : "No user input";
            if (response.contains("Bye")) {
                Platform.exit();
            }
            return response;
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }

}
