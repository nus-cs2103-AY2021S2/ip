package duke.command;

import duke.Duke;
import duke.TaskList;
import duke.TaskStorage;
import duke.Ui;

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
     */
    public void execute(TaskList tasks, Ui ui, TaskStorage storage) {
        Duke.toExit = true;
        ui.print("Goodbye. See you later!");
    }
}
