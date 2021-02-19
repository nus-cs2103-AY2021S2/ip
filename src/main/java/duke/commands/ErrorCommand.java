package duke.commands;

import duke.tasks.TaskList;

/**
 * Command to return error message for unknown input
 */
public class ErrorCommand extends Command {
    public String error;

    /**
     * Error command constructor
     *
     */
    public ErrorCommand(String error) {
        super(CommandType.ERROR);
        this.error = error;
    }

    /**
     * Prints unknown input error message
     * @param list List of tasks
     */
    @Override
    public String execute(TaskList list) {
        return this.error;
    }

}
