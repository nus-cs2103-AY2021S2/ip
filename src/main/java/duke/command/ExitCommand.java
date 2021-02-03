package duke.command;

import duke.util.TaskList;
import duke.util.TaskStorage;
import duke.util.Ui;

/**
 * Class representing an Exit Command.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     *
     * @param tasks List of tasks.
     * @param ui Formats and prints message to user.
     * @param storage
     * @return false.
     */
    public String execute(TaskList tasks, Ui ui, TaskStorage storage) {
        return ("Goodbye. See you later!");
    }

    public boolean toExit() {
        return true;
    }
}
