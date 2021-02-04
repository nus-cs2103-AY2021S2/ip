package duke.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of Task representing a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline of task.
     * @return deadline of task
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Returns the string representation of deadline.
     * @return string representation of deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
