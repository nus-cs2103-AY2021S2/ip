package duke.commands;

import duke.tasks.TaskList;

/**
 * Command to return error message for unknown input
 */
public class ErrorCommand extends Command {

    /**
     * Error command constructor
     *
     */
    public ErrorCommand() {
        super(CommandType.ERROR);
    }

    /**
     * Prints unknown input error message
     * @param list List of tasks
     */
    @Override
    public String execute(TaskList list) {
        return ui.printUnknowInputError();
    }

}
