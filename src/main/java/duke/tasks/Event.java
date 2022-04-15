package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Models an event task with a date and time.
 */
public class Event extends Task {
    private LocalDateTime dateTime;

    /**
     * Constructs a Event task object. Overloaded constructor to take in a String dateTime parameter.
     *
     * @param description the description of the Event task.
     * @param dateTime the date and time this task is due, in String representation.
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = LocalDateTime.parse(dateTime, FORMATTER);
    }

    /**
     * Constructs a Event task object. Overloaded constructor to take in a LocalDateTime dateTime parameter.
     *
     * @param description the description of the Event task.
     * @param dateTime the date and time this task is due, as a LocalDateTime.
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a"));
    }

    /**
     * Returns a specific String format of this Event's date to store locally when writing to file.
     *
     * @return String format of this Event's date.
     */
    public String getDateForFile() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Returns String description of this Event task, identified by "[E]", along with the date of this Event.
     *
     * @return String description.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDateTime() + ")";
    }
}
