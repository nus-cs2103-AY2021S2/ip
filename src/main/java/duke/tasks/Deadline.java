package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Models a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime dateTime;

    /**
     * Constructs a Deadline task object. Overloaded constructor to take in a String dateTime parameter.
     *
     * @param description the description of the Deadline task.
     * @param dateTime the date and time this task is due, in String representation.
     */
    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = LocalDateTime.parse(dateTime, FORMATTER);
    }

    /**
     * Constructs a Deadline task object. Overloaded constructor to take in a LocalDateTime dateTime parameter.
     *
     * @param description the description of the Deadline task.
     * @param dateTime the date and time this task is due, as a LocalDateTime.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a"));
    }

    /**
     * Returns a specific String format of this Deadline's date to store locally when writing to file.
     *
     * @return String format of this Deadline's date.
     */
    public String getDateForFile() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Returns String description of this Deadline task, identified by "[D]", along with the date of this Deadline.
     *
     * @return String description.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDateTime() + ")";
    }
}
