package duke.commands;

import static duke.utils.Messages.MESSAGE_DELETED_TASK;
import static duke.utils.Messages.MESSAGE_INVALID_INDEX;
import static duke.utils.Messages.MESSAGE_TASKLIST_SIZE_FORMAT;

import duke.tasks.Task;

/**
 * Deletes a task.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "Command Word: " + COMMAND_WORD + "\n"
            + "Description: Delete a task\n"
            + "Usage: delete <task_number>\n"
            + "Example: delete 2";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        try {
            Task task = taskList.getTask(index);
            taskList.deleteTask(index);
            return new CommandResult(MESSAGE_DELETED_TASK + "\n  " + task.toString() + "\n"
                    + String.format(MESSAGE_TASKLIST_SIZE_FORMAT, taskList.size()), taskList);
        } catch (IndexOutOfBoundsException ex) {
            return new CommandResult(MESSAGE_INVALID_INDEX);
        }
    }
}
