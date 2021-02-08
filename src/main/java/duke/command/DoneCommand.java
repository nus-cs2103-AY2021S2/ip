package duke.command;

import duke.exception.InvalidDescriptionException;
import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;
import duke.model.Task;

/**
 * The DoneCommand class denotes a done command to the Duke chat bot.
 */
public class DoneCommand extends Command {
    /**
     * Constructs an DoneCommand.
     * @param taskDescription   The description of the task.
     */
    public DoneCommand(String taskDescription) {
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
            taskList.done(index, storage);
            return ui.doneCommandInteraction(task);
        } catch (Exception ex) {
            return "     " + ex.getMessage();
        }
    }
}
