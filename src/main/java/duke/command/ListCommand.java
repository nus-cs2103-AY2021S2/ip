package duke.command;

import duke.util.MessageFormatter;
import duke.util.TaskList;
import duke.util.TaskStorage;

/**
 * Class representing a List Command.
 */
public class ListCommand extends Command {
    /**
     * Executes the List command.
     * Shows all the tasks to user.
     *
     * @param tasks List of tasks.
     * @param messageFormatter Formats Duke's response into a String.
     * @param storage Storage of tasks.
     * @return All current tasks.
     */
    public String execute(TaskList tasks, MessageFormatter messageFormatter, TaskStorage storage) {
        return messageFormatter.formatListMsg(tasks);
    }
}
