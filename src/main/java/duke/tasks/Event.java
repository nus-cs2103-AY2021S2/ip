package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a task defined by a time at which it will occur.
 */
public class Event extends Task implements DueDate {
    protected LocalDate time;

    /**
     * Constructs an event with its description, and its time.
     * The event has yet to happen.
     *
     * @param description A String description of the event.
     * @param time Time of the event in yyyy-MM-dd format (e.g. 2021-03-12).
     */
    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructs an event with its description, time, and its specified completion status.
     *
     * @param description A String description of the event.
     * @param isDone A boolean variable indicating if the event has happened.
     * @param time Time of the event in yyyy-MM-dd format (e.g. 2021-03-12).
     */
    public Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Marks an event as happened.
     *
     * @return A completed event with the same description.
     */
    public Event markAsDone() {
        return new Event(this.description, true, this.time);
    }

    /**
     * Returns the time of the event.
     *
     * @return A String representation of the time of the event.
     */
    @Override
    public String getDueDate() {
        return this.time.toString();
    }

    /**
     * Returns a String representation of the event.
     *
     * @return A String representation of the event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (at: " + this.time.format(formatter) + ")";
    }
}
