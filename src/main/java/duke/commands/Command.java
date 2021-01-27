package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Executes the command with the input Duke context (task list and storage).
     *
     * @param tasks The task list used by Duke.
     * @param storage The storage used by Duke.
     * @return The result of executing the command.
     */
    public abstract CommandResult execute(TaskList tasks, Storage storage);
}
