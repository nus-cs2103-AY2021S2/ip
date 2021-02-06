package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <code>Event</code> class represents any event task with a description and date.
 */
public class Event extends Task {
    private final LocalDateTime date;

    /**
     * Constructor for <code>Event</code> class
     *
     * @param description Description of the event task.
     * @param date Date of the event task.
     */
    public Event(String description, LocalDateTime date) {
        super (description);
        this.date = date;
        this.taskType = "Event";
    }

    /**
     * Generates the date of the event task.
     *
     * @return Date of the task in String format.
     */
    @Override
    public String getTaskDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return this.date.format(formatter);
    }

    /**
     * Generates details of an event task - description, date.
     *
     * @return String output for a event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + getTaskDate() + ")";
    }
}
