package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a single event created by the user via user input to the Duke program.
 * It contains functions which enable the user to mark the task as done,
 * and a date which the event is held on.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class Event extends Task {
    /** Date which the event is held on. */
    private final LocalDate date;

    /**
     * Default constructor for the Event class.
     *
     * @param name Description of the event.
     * @param date Date which the event is held on.
     */
    public Event(String name, LocalDate date) {
        super(name);
        this.date = date;

        assert(this.name != null && this.date != null);
    }

    /**
     * Returns the date the Event is held on, formatted as a String.
     *
     * @return The date the Event is held on, formatted as a String.
     */
    public String getDate() {
        return date.toString();
    }

    /**
     * Returns the date the Event is held on, formatted as a LocalDate.
     *
     * @return The date the Event is held on, formatted as a LocalDate.
     */
    public LocalDate getDateAsLocalDate() {
        return date;
    }

    /**
     * Returns a description of the event, formatted with its type,
     * followed by an "X" if it has been marked as done.
     *
     * @return Formatted description of the event.
     */
    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + name + " (at: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
