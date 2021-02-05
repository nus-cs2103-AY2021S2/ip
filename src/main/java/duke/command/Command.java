package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.data.DataStorage;
import duke.exception.DukeException;

import java.io.IOException;

/**
 * A class that interacts with TaskList to add and modify task
 */
public class Command {

    protected static String input;
    protected static TaskList tasklist = new TaskList();
    protected static DataStorage storage;
    protected static UI ui;

    /** Create command object
     *
     * @param input
     */
    public Command (String input) {
        this.input = input;
    }

    /**
     * Allow each command to execute their own function
     *
     * @return
     * @throws DukeException
     */
    public String execute() throws DukeException, IOException {
        tasklist.setTaskArraylist(storage.loadFile());
        return null;
    }

}
