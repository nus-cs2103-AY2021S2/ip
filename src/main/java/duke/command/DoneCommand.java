package duke.command;

import duke.exception.DukeException;

/**
 * Command class to mark task as complete
 */
public class DoneCommand extends Command {

    /**
     * Create a done command
     */
    public DoneCommand(String input) {
        super(input);
    }

    /**
     * Set task status of a specific task to complete and store the new changes to the data file
     * @return String message upon successful execution of the command
     */
    @Override
    public String execute() throws DukeException {

        String output = tasklist.markAsDone(Integer.parseInt(input) - 1);
        storage.save(tasklist.getTaskListArray());
        return output;
    }
}
