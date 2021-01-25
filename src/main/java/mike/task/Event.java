package mike.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Task that needs to be completed on the date
 */
public class Event extends Task {
    protected final String time;
    protected LocalDate date;

    /**
     * Creates an event with a description and time of the event
     *
     * @param description a description of the event
     * @param time the time of the event
     */
    public Event (String description, String time) {
        super(description);
        this.time = time;
        date = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
