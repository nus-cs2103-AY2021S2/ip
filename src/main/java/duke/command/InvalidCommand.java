package duke.command;

import duke.TaskList;
import duke.TaskStorage;
import duke.Ui;

/**
 * Class representing an Invalid Command.
 */
public class InvalidCommand extends Command {
    /**
     * Prompts user of invalid command.
     *
     * @param tasks
     * @param ui Formats and prints message to user.
     * @param storage
     * @return true.
     */
    public boolean execute(TaskList tasks, Ui ui, TaskStorage storage) {
        ui.print("Invalid command!");
        return true;
    }
}
