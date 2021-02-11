package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class for event tasks
 */
public class Event extends Task {

    protected String date;
    protected LocalDate formattedDate;

    /**
     * Constructor for event class.
     * Initializes a event with specified description.
     *
     * @param description description of the event task
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
        this.formattedDate = LocalDate.parse(date);
        this.type = 'E';
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public LocalDate getFormattedDate() {
        return this.formattedDate;
    }

    public String getFormattedDateString() {
        return formattedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getFormattedDateString() + ")";
    }
}
