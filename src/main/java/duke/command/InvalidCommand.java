package duke.command;

import duke.util.TaskList;
import duke.util.TaskStorage;
import duke.util.Ui;

/**
 * Class representing an Invalid Command.
 */
public class InvalidCommand extends Command {
    private String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Prompts user of invalid command.
     *
     * @param tasks
     * @param ui Formats and prints message to user.
     * @param storage
     * @return true.
     */
    public String execute(TaskList tasks, Ui ui, TaskStorage storage) {
        return errorMessage;
    }
}
