package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Represents a task manager
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private boolean isClosed;
    private boolean isConfused;

    /**
     * Class constructor with specified file path.
     * @param filePath The path to load and save from
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        isClosed = false;
        isConfused = false;
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
            if (c.isExit()) {
                isClosed = true;
            } else {
                isClosed = false;
            }
            String response = c.getResponString(tasks, storage);
            isConfused = false;
            return response;
        } catch (DukeException e) {
            isConfused = true;
            return e.getMessage();
        }
    }

    /**
     * Gets the welcome string
     * @return Welcome string
     */
    public String getWelcome() {
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        return welcomeMessage;
    }

    /**
     * Checks if previous command was an exit command
     * @return true if previous command was an exit command and false otherwise
     */
    public boolean isClosed() {
        return this.isClosed;
    }

    /**
     * Checks duke is confused by the last command
     * @return true if duke is confused and false otherwise
     */
    public boolean isConfused() {
        return this.isConfused;
    }
}
