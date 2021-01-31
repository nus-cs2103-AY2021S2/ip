
package dukeproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task where we can specific a task to be completed by a certain date time.
 * A deadline object corresponds to a task with a description and
 * a datetime for the task to be completed.
 *
 * X means that the task has been completed.
 */
public class Deadline extends Task {

    private final LocalDateTime by;

    /**
     * Constructor for the Deadline task, specifying the description of the task and
     * the date time to be completed by.
     * @param description Description of the deadline task.
     * @param by Deadline in terms of date time.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for the Deadline task, specifying the description of the task,
     * the date time to be completed by and whether the task has been completed.
     * @param description Description of the deadline task.
     * @param by Deadline in terms of date time.
     * @param isDone Determine whether the task is done or not.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a readable description of the task.
     * E.g. [D][X] Borrow Book (by: 27 Oct 2021 - 1800)
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
            super.getStatusIcon(),
            super.toString(),
            by.format(DateTimeFormatter.ofPattern("d MMM yyyy - HHmm")));
    }
}
