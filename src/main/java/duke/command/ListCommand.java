package duke.command;

import duke.TaskList;
import duke.TaskStorage;
import duke.Ui;

/**
 * Class representing a List Command.
 */
public class ListCommand extends Command {
    /**
     * Executes the List command.
     * Shows all the tasks to user.
     * @param tasks List of tasks.
     * @param ui Formats and prints tasks to user.
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, TaskStorage storage) {
        ui.print(tasks);
    }
}
