package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Initialises the task with a description and
     * a deadline represented by a date.
     *
     * @param description Description of the task.
     * @param by Task deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Retrieves the deadline of the task.
     *
     * @return Task deadline.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Formats the task string representation.
     *
     * @return Formatted string.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}
