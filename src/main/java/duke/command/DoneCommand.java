package duke.command;

import duke.exception.DukeException;

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
     * @return String message upon successful execution of the command
     */
    @Override
    public String execute() throws DukeException {
        String output = tasklist.markAsDone(Integer.parseInt(input) - 1);
        storage.save(tasklist.getTaskListArray());
        return output;
    }
}
