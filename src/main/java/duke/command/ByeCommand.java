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
     * @param taskList A list of recorded tasks.
     * @param ui       A user interface.
     * @param storage  A list of recorded user inputs data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.byeCommandInteraction();
    }

    /**
     * Exit the Duke program
     * @return   A signal that indicate the termination of the Duke program.
     */
    public boolean isExit() {
        return true;
    }
}
