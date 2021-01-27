package duke.command;

import duke.TaskList;
import duke.TaskStorage;
import duke.Ui;

/**
 * Class representing a Command.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param tasks List of tasks.
     * @param ui Formats and prints message to user.
     * @param storage Updates the tasks.txt file after executing commands modifying TaskList/tasks.
     */
    public abstract void execute(TaskList tasks, Ui ui, TaskStorage storage);
}
