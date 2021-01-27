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
     */
    public void execute(TaskList tasks, Ui ui, TaskStorage storage) {
        ui.print("Invalid command!");
    }
}
