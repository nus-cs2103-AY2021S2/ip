package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.data.DataStorage;
import duke.exception.DukeException;

/**
 * Create delete command object
 */
public class DeleteCommand extends Command {

    /**
     * Constructor to create delete command object
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /** Delete task
     *  Store the new changes back to data file
     * @return array of task list
     * @throws DukeException
     */
    @Override
    public String execute() throws DukeException {
        String output = this.tasklist.deleteTask(Integer.parseInt(input) - 1);
        storage.save(this.tasklist.getTaskListArray());
        return output;
    }
}
