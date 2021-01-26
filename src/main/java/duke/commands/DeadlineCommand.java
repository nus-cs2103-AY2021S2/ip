package duke.commands;

import duke.tasks.DeadlineTask;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String ADDED_TASK_MESSAGE = "Got it. I've added this task:\n  ";
    public static final String TASKLIST_SIZE_MESSAGE_FORMAT = "Now you have %d tasks in your list.";

    private String taskName;
    private LocalDateTime deadline;

    public DeadlineCommand(String taskName, LocalDateTime deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute() {
        DeadlineTask task = new DeadlineTask(taskName, deadline);
        taskList.addTask(task);
        return new CommandResult(ADDED_TASK_MESSAGE + task.toString() + "\n" +
                String.format(TASKLIST_SIZE_MESSAGE_FORMAT, taskList.size()), taskList);
    }
}
