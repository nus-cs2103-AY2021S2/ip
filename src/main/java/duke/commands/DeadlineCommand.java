package duke.commands;

import duke.tasks.DeadlineTask;

import java.time.LocalDateTime;

/**
 * Creates a Deadline task.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private static final String MESSAGE_ADDED_TASK = "Got it. I've added this task:\n  ";
    private static final String MESSAGE_TASKLIST_SIZE_FORMAT = "Now you have %d tasks in your list.";

    private final String taskName;
    private final LocalDateTime deadline;

    public DeadlineCommand(String taskName, LocalDateTime deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute() {
        DeadlineTask task = new DeadlineTask(taskName, deadline);
        taskList.addTask(task);
        return new CommandResult(MESSAGE_ADDED_TASK + task.toString() + "\n"
                + String.format(MESSAGE_TASKLIST_SIZE_FORMAT, taskList.size()), taskList);
    }
}
