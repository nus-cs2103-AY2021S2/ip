package zeke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class for event tasks
 */
public class Event extends Task {

    protected String date;
    protected LocalDate localDate;

    /**
     * Constructor for event class.
     * Initializes an event with specified description and date.
     *
     * @param description description of the event task.
     * @param date date of the event task.
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
        this.localDate = LocalDate.parse(date);
        this.type = 'E';
    }

    /**
     * Returns date of this event.
     *
     * @return the date of this event.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Returns the parsed LocalDate.
     *
     * @return the LocalDate of this event.
     */
    public LocalDate getLocalDate() {
        return this.localDate;
    }

    /**
     * Returns an event in MMM dd yyyy format.
     *
     * @return a formatted event.
     */
    public String getFormattedDateString() {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getFormattedDateString() + ")";
    }
}
