package duke.command;

import java.io.IOException;

import duke.data.DataStorage;
import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Command class that interacts with TaskList class to add, show, delete
 *  find task and marking task as completed
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
        tasklist.setTaskList(storage.loadFile());
        return null;
    }

}
