package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {

    /**
     * Constructor
     * @param message Deadline details
     * @param date Due date
     */
    public Deadline(String message, LocalDate date) {
        super("D", message, date);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
