package duke.tasks;

import duke.utils.OutputDateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    public static final String IDENTIFIER = "D";

    private final LocalDate deadlineDate;
    private final LocalTime deadlineTime;

    public DeadlineTask(String name, LocalDate deadlineDate) {
        super(IDENTIFIER, name);
        this.deadlineDate = deadlineDate;
        deadlineTime = null;
    }

    public DeadlineTask(String name, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(IDENTIFIER, name);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    public DeadlineTask(String name, boolean isCompleted, LocalDate deadlineDate) {
        super(IDENTIFIER, name, isCompleted);
        this.deadlineDate = deadlineDate;
        deadlineTime = null;
    }

    public DeadlineTask(String name, boolean isCompleted, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(IDENTIFIER, name, isCompleted);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Returns the deadline in a nicely formatted string.
     *
     * @return formatted deadline string
     */
    public String getDeadline() {
        StringBuilder deadline = new StringBuilder();
        deadline.append(deadlineDate.format(OutputDateTimeFormat.OUTPUT_DATE_FORMAT));
        if (deadlineTime != null) {
            deadline.append(", ");
            deadline.append(deadlineTime.format(OutputDateTimeFormat.OUTPUT_TIME_FORMAT));
        }
        return deadline.toString();
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadline() + ")";
    }
}
