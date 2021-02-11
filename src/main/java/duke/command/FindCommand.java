package duke.command;

import duke.common.DukeString;
import duke.task.TaskList;

/**
 * Represents a command that adds a finds a String in Tasks from the given TaskList.
 */
public class FindCommand implements Command {
    private final String string;

    /**
     * Constructs a new FindCommand with the given String to search for.
     *
     * @param str the string to search the TaskList for.
     */
    public FindCommand(String str) {
        this.string = str;
    }

    /**
     * Finds the given String in Tasks from the given TaskList.
     *
     * @param taskList the TaskList to search through.
     * @return a formatted String, that contains the matching tasks, if any.
     */
    @Override
    public String execute(TaskList taskList) {
        return DukeString.MESSAGE_FIND + taskList.tasksContaining(this.string);
    }
}
