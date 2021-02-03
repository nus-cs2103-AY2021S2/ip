package duke.command;

import duke.util.TaskList;
import duke.util.TaskStorage;
import duke.util.Ui;

/**
 * Class representing a Command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks List of tasks.
     * @param ui Formats and prints message to user.
     * @param storage Updates the tasks.txt file after executing commands modifying TaskList/tasks.
     * @return false if exit command is executed, true otherwise.
     */
    public abstract String execute(TaskList tasks, Ui ui, TaskStorage storage);

    public boolean toExit() {
        return false;
    };
}
