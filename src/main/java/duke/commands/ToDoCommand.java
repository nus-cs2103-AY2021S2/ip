package duke.commands;

import duke.tasks.ToDoTask;

/**
 * Creates a ToDo task.
 */
public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private static final String MESSAGE_ADDED_TASK = "Got it. I've added this task:\n  ";
    private static final String MESSAGE_TASKLIST_SIZE_FORMAT = "Now you have %d tasks in your list.";

    private final String taskName;

    public ToDoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public CommandResult execute() {
        ToDoTask task = new ToDoTask(taskName);
        taskList.addTask(task);
        return new CommandResult(MESSAGE_ADDED_TASK + task.toString() + "\n"
                + String.format(MESSAGE_TASKLIST_SIZE_FORMAT, taskList.size()), taskList);
    }
}
