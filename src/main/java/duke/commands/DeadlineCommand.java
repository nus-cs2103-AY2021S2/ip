package duke.commands;

import duke.tasks.DeadlineTask;

import java.time.LocalDate;
import java.time.LocalTime;

import static duke.utils.Messages.MESSAGE_ADDED_TASK;
import static duke.utils.Messages.MESSAGE_TASKLIST_SIZE_FORMAT;

/**
 * Creates a Deadline task.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = "Command Word: " + COMMAND_WORD + "\n"
            + "Description: Add a deadline task with a date component and an optional time component\n"
            + "Usage 1: deadline <task_description> /by dd/mm/yyyy\n"
            + "Example: deadline Assignment 1 /by 31/1/2021\n"
            + "Usage 2: deadline <task_description> /by dd/mm/yyyy HHHH\n"
            + "Example: deadline Assignment 1 /by 31/1/2021 1800";

    private final String taskName;
    private final LocalDate deadlineDate;
    private final LocalTime deadlineTime;

    public DeadlineCommand(String taskName, LocalDate deadlineDate) {
        this.taskName = taskName;
        this.deadlineDate = deadlineDate;
        deadlineTime = null;
    }

    public DeadlineCommand(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
        this.taskName = taskName;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public CommandResult execute() {
        DeadlineTask task = new DeadlineTask(taskName, deadlineDate, deadlineTime);
        taskList.addTask(task);
        return new CommandResult(MESSAGE_ADDED_TASK + "\n  " + task.toString() + "\n"
                + String.format(MESSAGE_TASKLIST_SIZE_FORMAT, taskList.size()), taskList);
    }
}
