package duke.command;

import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;

/**
 * The ListCommand class denotes a list command to the Duke chat bot.
 */
public class ListCommand extends Command {
    /**
     * Constructs an ListCommand.
     * @param taskDescription   The description of the task.
     */
    public ListCommand(String taskDescription) {
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
        ui.listCommandInteraction(taskList);
    }

    /**
     * Do not exit the Duke program
     * @return   A signal that indicate a continuation of the Duke program.
     */
    public boolean isExit() {
        return false;
    }
}
