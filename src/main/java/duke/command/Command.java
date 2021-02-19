package duke.command;

import duke.logic.Storage;
import duke.task.TaskList;

/**
 * Represents a command to duke
 */
public interface Command {
    /**
     * Checks if the command is an exit command
     * @return True if is an exit command, false otherwise
     */
    public boolean isExit();

    /**
     * Executes the command and gets the response
     * @param tasks List of tasks to operate on
     * @param storage Storage manager for loading and saving task files
     * @return Duke's response after executing the command
     */
    public String execute(TaskList tasks, Storage storage);
}
