package duke.command;

import duke.logic.Storage;
import duke.task.TaskList;

/**
 * Represents a command telling duke to list all current tasks
 */
public class ListCommand implements Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        String listResponse = tasks.toString();
        return listResponse;
    }
}
