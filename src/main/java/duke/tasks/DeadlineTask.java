package duke.tasks;

import duke.utils.Formatter;

import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    public static final String IDENTIFIER = "D";

    private LocalDateTime deadline;

    public DeadlineTask(String name, LocalDateTime deadline) {
        super(IDENTIFIER, name);
        this.deadline = deadline;
    }

    public DeadlineTask(String name, boolean isCompleted, LocalDateTime deadline) {
        super(IDENTIFIER, name, isCompleted);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline in a nicely formatted string.
     *
     * @return formatted deadline string
     */
    public String getDeadline() {
        return deadline.format(Formatter.OUTPUT_DATE_FORMATTER);
    }

    @Override
    public String getTaskType() {
        return IDENTIFIER;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadline() + ")";
    }
}
