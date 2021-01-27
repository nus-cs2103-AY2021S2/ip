package duke.commands;

import duke.tasks.Task;

/**
 * Deletes a task.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private static final String MESSAGE_DELETED_TASK = "Noted. I've removed this task:\n  ";
    private static final String MESSAGE_TASKLIST_SIZE_FORMAT = "Now you have %d tasks in your list.";
    private static final String MESSAGE_INVALID_INDEX = "Please enter a valid index!";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        try {
            Task task = taskList.getTask(index);
            taskList.deleteTask(index);
            return new CommandResult(MESSAGE_DELETED_TASK + task.toString() + "\n"
                    + String.format(MESSAGE_TASKLIST_SIZE_FORMAT, taskList.size()), taskList);
        } catch (IndexOutOfBoundsException ex) {
            return new CommandResult(MESSAGE_INVALID_INDEX);
        }
    }
}
