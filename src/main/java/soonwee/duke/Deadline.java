package soonwee.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline instance. A Deadline instance will store the task
 * name and its due completion time.
 */
public class Deadline extends Task {

    protected LocalDateTime endTime;

    /**
     * Instantiates the Deadline task.
     *
     * @param description description of the task
     * @param endTime deadline to complete the task by
     */
    public Deadline(String description, LocalDateTime endTime) {
        super(description);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}