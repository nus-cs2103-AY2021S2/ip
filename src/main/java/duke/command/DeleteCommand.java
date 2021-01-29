package duke.command;

import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;

/**
 * The DeleteCommand class denotes a delete command to the Duke chat bot.
 */
public class DeleteCommand extends Command {
    /**
     * Constructs an DeleteCommand.
     * @param taskDescription   The description of the task.
     */
    public DeleteCommand(String taskDescription) {
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
        try {
            storage.overwrite(taskList.delete(taskDescription, ui));
        } catch (Exception ex) {
            System.out.println("     " + ex.getMessage());
        }
    }

    /**
     * Do not exit the Duke program
     * @return   A signal that indicate a continuation of the Duke program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
