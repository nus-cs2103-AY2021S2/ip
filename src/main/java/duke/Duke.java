package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents a task manager
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Class constructor with specified file path.
     * 
     * @param filePath The path to load and save from
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (IOException e) {
            tasks = new TaskList();
        } catch (DateTimeParseException e) {
            tasks = new TaskList();
        }
    }


    /**
     * Runs the duke programme.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.getResponString(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String getWelcome() {
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        return welcomeMessage;
    }
}
