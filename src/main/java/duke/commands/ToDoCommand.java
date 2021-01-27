package duke.commands;

import duke.tasks.ToDoTask;

import static duke.utils.Messages.MESSAGE_ADDED_TASK;
import static duke.utils.Messages.MESSAGE_TASKLIST_SIZE_FORMAT;

/**
 * Creates a ToDo task.
 */
public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

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
