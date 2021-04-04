package duke.handler;

import duke.Storage;
import duke.tasks.TaskList;

/**
 * Interface for command execution.
 */
public interface CommandHandler {
    public String execute(Storage storage, TaskList taskList);
}
