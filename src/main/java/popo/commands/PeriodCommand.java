package popo.commands;

import static popo.utils.Messages.MESSAGE_ADDED_TASK;
import static popo.utils.Messages.MESSAGE_TASKLIST_SIZE_FORMAT;

import java.time.LocalDate;

import popo.tasks.PeriodTask;

/**
 * Creates a Period task.
 */
public class PeriodCommand extends Command {
    public static final String COMMAND_WORD = "period";
    public static final String MESSAGE_USAGE = "Command Word: " + COMMAND_WORD + "\n"
            + "Description: Add a period task with a starting date and an ending date\n"
            + "Usage: period <task_description> /start dd/mm/yyyy /end dd/mm/yyyy\n"
            + "Example: period Collect certificate at NUS /start 1/3/2021 /end 31/3/2021";

    private final String taskName;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Creates a {@code PeriodCommand} object with the given task name, starting date and ending date.
     *
     * @param taskName  Name of the task.
     * @param startDate Starting date of the task.
     * @param endDate  Ending date of the task.
     */
    public PeriodCommand(String taskName, LocalDate startDate, LocalDate endDate) {
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CommandResult execute() {
        assert taskList != null;
        PeriodTask task = new PeriodTask(taskName, startDate, endDate);
        taskList.addTask(task);
        return new CommandResult(taskList, false,
                MESSAGE_ADDED_TASK + "\n",
                task.toString() + "\n",
                String.format(MESSAGE_TASKLIST_SIZE_FORMAT, taskList.size()));
    }
}
