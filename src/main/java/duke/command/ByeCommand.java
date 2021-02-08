package duke.command;

import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;

/**
 * The ByeCommand class denotes an bye command to the Duke chat bot.
 */
public class ByeCommand extends Command {
    /**
     * Constructs an DeleteCommand.
     * @param taskDescription The description of the task.
     */
    public ByeCommand(String taskDescription) {
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
        return ui.byeCommandInteraction();
    }
}
