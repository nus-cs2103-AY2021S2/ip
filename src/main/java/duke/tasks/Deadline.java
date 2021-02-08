package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final LocalDateTime byDateTime;

    /**
     * Initializes a deadline task with a description and a datetime.
     *
     * @param description Description of the deadline task.
     * @param byDateTime  The task's deadline.
     */
    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
    }

    /**
     * Converts the task's deadline to a <code>String</code>.
     *
     * @return A formatted date string corresponding to the task's deadline.
     */
    public String getByDateTimeString() {
        return this.byDateTime.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
    }

    /**
     * Retrieves the deadline task's description and status.
     *
     * @return A formatted string displaying the deadline task's description and status.
     */
    public String getStatusString() {
        return "[D]" + super.getStatusString() + " (by: " + this.getByDateTimeString() + ")";
    }
}
