package duke.commands;

import static duke.utils.Messages.MESSAGE_ADDED_TASK;
import static duke.utils.Messages.MESSAGE_TASKLIST_SIZE_FORMAT;

import duke.tasks.ToDoTask;

/**
 * Creates a ToDo task.
 */
public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = "Command Word: " + COMMAND_WORD + "\n"
            + "Description: Add a todo task\n"
            + "Usage: todo <task_description>\n"
            + "Example: todo Go CNY shopping";

    private final String taskName;

    /**
     * Creates a {@code ToDoCommand} with the given task name.
     *
     * @param taskName Name of the task.
     */
    public ToDoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public CommandResult execute() {
        ToDoTask task = new ToDoTask(taskName);
        taskList.addTask(task);
        String messageForUser = MESSAGE_ADDED_TASK + "\n"
                + "  " + task.toString() + "\n"
                + String.format(MESSAGE_TASKLIST_SIZE_FORMAT, taskList.size());
        return new CommandResult(messageForUser, taskList, false);
    }
}
