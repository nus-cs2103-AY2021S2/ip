package soonwee.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline instance. A Deadline instance will store the task
 * name and its due completion time.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Instantiates the Deadline task.
     *
     * @param description description of the task.
     * @param by deadline to complete the task by.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}