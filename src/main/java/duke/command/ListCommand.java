package duke.command;

import duke.util.TaskList;
import duke.util.TaskStorage;
import duke.util.Ui;

/**
 * Class representing a List Command.
 */
public class ListCommand extends Command {
    /**
     * Executes the List command.
     * Shows all the tasks to user.
     *
     * @param tasks List of tasks.
     * @param ui Formats and prints tasks to user.
     * @param storage
     * @return true.
     */
    public String execute(TaskList tasks, Ui ui, TaskStorage storage) {
        return ui.formatListCmdMsg(tasks);
    }
}
