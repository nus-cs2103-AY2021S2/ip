package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.data.DataStorage;
import duke.exception.DukeException;

import java.io.IOException;

/**
 * A class that interacts with TaskList class to add, show, delete, find task as well as marking them as completed
 */
public class Command {

    protected static String input;
    protected static TaskList tasklist = new TaskList();
    protected static DataStorage storage;
    protected static UI ui = new UI();

    /**
     * Create command object
     */
    public Command (String input) {
        this.input = input;
    }

    /**
     * Retrieve past data from the data file and allow modification from other commands classes
     * @return null
     * @throws DukeException
     */
    public String execute() throws DukeException, IOException {
        tasklist.setTaskArraylist(storage.loadFile());
        return null;
    }

}
