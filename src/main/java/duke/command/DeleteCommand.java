package duke.command;

import java.util.ArrayList;

import duke.exception.InvalidDescriptionException;
import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;
import duke.model.Task;

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
     * @param taskList The list of recorded tasks.
     * @param ui       The user interface.
     * @param storage  The list of recorded user inputs data.
     * @return         The message replied by Duke chat bot.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (taskDescription.isEmpty()) {
                throw new InvalidDescriptionException("Description cannot be empty");
            }
            int index = Integer.parseInt(taskDescription.substring(0, 1)) - 1;
            Task task = taskList.getTask(index);
            ArrayList<Task> tasks = taskList.delete(index, storage);
            return ui.deleteCommandInteraction(task, tasks);
        } catch (Exception ex) {
            return "     " + ex.getMessage();
        }
    }
}
