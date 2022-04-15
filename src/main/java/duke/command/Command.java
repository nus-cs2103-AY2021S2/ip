package duke.command;

import duke.util.MessageFormatter;
import duke.util.TaskList;
import duke.util.TaskStorage;

/**
 * Class representing a Command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks List of tasks.
     * @param messageFormatter Formats Duke's response into a String.
     * @param storage Updates the tasks.txt file after executing commands modifying TaskList/tasks.
     * @return Duke's response after executing the command.
     */
    public abstract String execute(TaskList tasks, MessageFormatter messageFormatter, TaskStorage storage);

    /**
     * Returns whether the user has requested to exit the application.
     *
     * @return false for all commands except the ExitCommand, which overrides this method.
     */
    public boolean toExit() {
        return false;
    }
}
