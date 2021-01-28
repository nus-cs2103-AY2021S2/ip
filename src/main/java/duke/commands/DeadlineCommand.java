package duke.commands;

import duke.tasks.DeadlineTask;

import java.time.LocalDateTime;

import static duke.utils.Messages.MESSAGE_ADDED_TASK;
import static duke.utils.Messages.MESSAGE_TASKLIST_SIZE_FORMAT;

/**
 * Creates a Deadline task.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = "Command Word: " + COMMAND_WORD + "\n"
            + "Description: Add a deadline task\n"
            + "Usage: deadline <task_description> /by dd/mm/yyyy HHHH\n"
            + "Example: deadline Assignment 1 /by 31/1/2021 1800";

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
        return new CommandResult(MESSAGE_ADDED_TASK + "\n  " + task.toString() + "\n"
                + String.format(MESSAGE_TASKLIST_SIZE_FORMAT, taskList.size()), taskList);
    }
}
