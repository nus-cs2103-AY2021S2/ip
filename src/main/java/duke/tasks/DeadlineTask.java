package duke.tasks;

import duke.utils.Formatter;

import java.time.LocalDateTime;

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

    public String getDeadline() {
        return deadline.format(Formatter.OUTPUT_DATE_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadline() + ")";
    }
}
