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
        try {
            storage.store(tasks);
        } catch (IOException e) {
            throw new DukeException("Cannot save tasks. Save file not found");
        }

        String goodbyeResponse = "Bye. Hope to see you again soon!";
        return goodbyeResponse;
    }
}
