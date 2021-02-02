package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.utils.OutputDateTimeFormat;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    public static final String IDENTIFIER = "D";

    private final LocalDate DEADLINE_DATE;
    private final LocalTime DEADLINE_TIME;

    /**
     * Creates a {@code DeadlineTask} object with the given task name and deadline date component,
     * with the task set to initially not completed.
     *
     * @param name         Name of the task.
     * @param deadlineDate Fate component of deadline.
     */
    public DeadlineTask(String name, LocalDate deadlineDate) {
        super(IDENTIFIER, name);
        DEADLINE_DATE = deadlineDate;
        DEADLINE_TIME = null;
    }

    /**
     * Creates a {@code DeadlineTask} object with the given task name and deadline date and time components,
     * with the task set to initially not completed.
     *
     * @param name         Name of the task.
     * @param deadlineDate Date component of deadline.
     * @param deadlineTime Time component of deadline.
     */
    public DeadlineTask(String name, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(IDENTIFIER, name);
        DEADLINE_DATE = deadlineDate;
        DEADLINE_TIME = deadlineTime;
    }

    /**
     * Creates a {@code DeadlineTask} object with the given task name and deadline date component,
     * with the task set to the boolean isCompleted.
     *
     * @param name         Name of the task.
     * @param isCompleted  Boolean indicating whether the task has been completed.
     * @param deadlineDate Date component of deadline.
     */
    public DeadlineTask(String name, boolean isCompleted, LocalDate deadlineDate) {
        super(IDENTIFIER, name, isCompleted);
        DEADLINE_DATE = deadlineDate;
        DEADLINE_TIME = null;
    }

    /**
     * Creates a {@code DeadlineTask} object with the given task name and deadline date and time components,
     * with the task set to the boolean isCompleted.
     *
     * @param name         Name of the task.
     * @param isCompleted  Boolean indicating whether the task has been completed.
     * @param deadlineDate Date component of deadline.
     * @param deadlineTime Time component of deadline.
     */
    public DeadlineTask(String name, boolean isCompleted, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(IDENTIFIER, name, isCompleted);
        DEADLINE_DATE = deadlineDate;
        DEADLINE_TIME = deadlineTime;
    }

    /**
     * Returns the deadline in a nicely formatted string.
     *
     * @return Formatted deadline string.
     */
    public String getDeadline() {
        StringBuilder deadline = new StringBuilder();
        deadline.append(DEADLINE_DATE.format(OutputDateTimeFormat.OUTPUT_DATE_FORMAT));
        if (DEADLINE_TIME != null) {
            deadline.append(", ");
            deadline.append(DEADLINE_TIME.format(OutputDateTimeFormat.OUTPUT_TIME_FORMAT));
        }
        return deadline.toString();
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadline() + ")";
    }
}
