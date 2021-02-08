package duke.command;

import duke.exception.InvalidInputException;
import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;

/**
 * The UnknownCommand class denotes an unknown command to the Duke chat bot.
 */
public class UnknownCommand extends Command {
    /**
     * Constructs an UnknownCommand.
     * @param taskDescription   The description of the task.
     */
    public UnknownCommand(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Executing the command
     * @param taskList The list of recorded tasks.
     * @param ui       The user interface.
     * @param storage  The list of recorded user inputs data.
     * @return         The message replied by Duke chat bot.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            return ui.unknownCommandInteraction();
        } catch (InvalidInputException ex) {
            return ex.getMessage();
        }
    }
}
