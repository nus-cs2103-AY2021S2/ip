package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <code>Deadline</code> class represents any deadline task
 * with a description and date.
 */
public class Deadline extends Task {
    private final LocalDateTime date;

    /**
     * Constructor for <code>Deadline</code> class.
     * @param description Description of the deadline task.
     * @param date Date of the deadline task.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
        this.taskType = "Deadline";
    }

    /**
     * Generate the date of the deadline task.
     * @return Date of the task in String format.
     */
    @Override
    public String getTaskDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Generate details of a deadline task - description, date.
     * @return String output for a deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getTaskDate() + ")";
    }
}
