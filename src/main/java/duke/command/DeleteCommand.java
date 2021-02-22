package duke.command;

import duke.exception.DukeException;

/**
 * Command class to delete task
 */
public class DeleteCommand extends Command {

    /**
     * Create a delete command
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     *  Delete a specific task and store the new changes into the data file
     * @return String message upon successful execution of the command
     * @throws DukeException
     */
    @Override
    public String execute() throws DukeException {

        String output = this.tasklist.deleteTask(Integer.parseInt(input) - 1);
        storage.save(this.tasklist.getTaskListArray());
        return output;
    }
}
