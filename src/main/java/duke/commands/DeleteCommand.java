package duke.commands;

import duke.tasks.Task;

/**
 * Deletes a task.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private static final String DELETED_TASK_MESSAGE = "Noted. I've removed this task:\n  ";
    private static final String TASKLIST_SIZE_MESSAGE_FORMAT = "Now you have %d tasks in your list.";
    private static final String INVALID_INDEX_MESSAGE = "Please enter a valid index!";

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        try {
            Task task = taskList.getTask(index);
            taskList.deleteTask(index);
            return new CommandResult(DELETED_TASK_MESSAGE + task.toString() + "\n" +
                    String.format(TASKLIST_SIZE_MESSAGE_FORMAT, taskList.size()), taskList);
        } catch (IndexOutOfBoundsException ex) {
            return new CommandResult(INVALID_INDEX_MESSAGE);
        }
    }
}
