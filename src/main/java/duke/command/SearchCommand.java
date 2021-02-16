package duke.command;

import duke.exception.DukeException;

/**
 * Command class to search for task
 */

public class SearchCommand extends Command {

    /**
     * Create search command
     */
    public SearchCommand(String input) {
        super(input.trim());
    }

    /**
     * Display the result of task in the task list which matches or contains the given user keyword
     * @return String message upon successful execution of the command
     */
    @Override
    public String execute() throws DukeException {
        return tasklist.findTask(input, this.tasklist);
    }
}
