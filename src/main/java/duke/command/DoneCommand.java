package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.data.DataStorage;
import duke.exception.DukeException;

/**
 * Create done command class
 */
public class DoneCommand extends Command {

    /**
     * Constructor to create done command object
     */
    public DoneCommand(String input) {
        super(input);
    }

    /** Set and display task as completed
     * Store the new changes back to data file
     * @return
     */
    @Override
    public String execute() throws DukeException {
        String output = tasklist.markAsDone(Integer.parseInt(input) - 1);
        storage.save(tasklist.getTaskListArray());
        return output;
    }
}
