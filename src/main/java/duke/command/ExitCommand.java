package duke.command;

import duke.logic.Storage;
import duke.task.TaskList;

/**
 * Represents a command telling duke to close
 */
public class ExitCommand implements Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        String goodbyeResponse = "Bye. Hope to see you again soon!";
        return goodbyeResponse;
    }
}
