package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
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
    public String getResponString(TaskList tasks, Storage storage) {
        String goodbyeResponse = "Bye. Hope to see you again soon!";
        return goodbyeResponse;
    }
}
