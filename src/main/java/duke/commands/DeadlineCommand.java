package duke.commands;

import static duke.utils.Messages.MESSAGE_ADDED_TASK;
import static duke.utils.Messages.MESSAGE_TASKLIST_SIZE_FORMAT;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.tasks.DeadlineTask;

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
            + "Example: deadline Assignment 1 /by 31/1/2021 1800\n"
            + "Example: deadline Assignment 2 /by 28/10/2021, 2359";

    private final String TASK_NAME;
    private final LocalDate DEADLINE_DATE;
    private final LocalTime DEADLINE_TIME;

    /**
     * Creates a {@code DeadlineCommand} object with a deadline date component only.
     *
     * @param taskName     Name of the task.
     * @param deadlineDate Date component of deadline.
     */
    public DeadlineCommand(String taskName, LocalDate deadlineDate) {
        TASK_NAME = taskName;
        DEADLINE_DATE = deadlineDate;
        DEADLINE_TIME = null;
    }

    /**
     * Creates a {@code DeadlineCommand} object with the given task name, deadline date and time components.
     *
     * @param taskName     Name of the task.
     * @param deadlineDate Date component of deadline.
     * @param deadlineTime Time component of deadline.
     */
    public DeadlineCommand(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
        TASK_NAME = taskName;
        DEADLINE_DATE = deadlineDate;
        DEADLINE_TIME = deadlineTime;
    }

    @Override
    public CommandResult execute() {
        DeadlineTask task = new DeadlineTask(TASK_NAME, DEADLINE_DATE, DEADLINE_TIME);
        taskList.addTask(task);
        String messageForUser = MESSAGE_ADDED_TASK + "\n"
                + "  " + task.toString() + "\n"
                + String.format(MESSAGE_TASKLIST_SIZE_FORMAT, taskList.size());
        return new CommandResult(messageForUser, taskList, false);
    }
}
