package duke.command;

import duke.util.TaskList;
import duke.util.TaskStorage;
import duke.util.MessageFormatter;

/**
 * Class representing an Exit Command.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     *
     * @param tasks List of tasks.
     * @param messageFormatter Formats Duke's response into a String.
     * @param storage Storage of tasks.
     * @return A goodbye message from Duke.
     */
    public String execute(TaskList tasks, MessageFormatter messageFormatter, TaskStorage storage) {
        return ("Goodbye. See you later!");
    }

    /**
     * Method that returns whether the user has requested to exit the application.
     *
     * @return true, as this is the Exit Command.
     */
    @Override
    public boolean toExit() {
        return true;
    }
}
