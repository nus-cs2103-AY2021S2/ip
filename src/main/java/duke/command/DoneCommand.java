package duke.command;

import duke.exception.DukeException;
import duke.ui.UI;

import java.util.ArrayList;

/**
 * Command class to mark task as complete
 */
public class DoneCommand extends Command {

    /**
     * Create done command
     */
    public DoneCommand(String input) {
        super(input);
    }

    /**
     * Set task status of a specific task to completed and store the new changes back to the data file
     *
     * @return String message upon successful execution of the command
     */
    @Override
    public String execute() throws DukeException {

        int task_index = Integer.parseInt(input) - 1;

        if (task_index < 0) {
            throw new DukeException("Task index starts from 1. Please try again with a valid task index.");
        } else if (tasklist.getTaskListArray().isEmpty() || tasklist.getTaskListArray().size() < task_index) {
            throw new DukeException(UI.displayNoTaskMessage());
        } else {
            String output = tasklist.markAsDone(Integer.parseInt(input) - 1);
            storage.save(tasklist.getTaskListArray());
            return output;
        }
    }
}
