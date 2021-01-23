package duke.task;

import java.time.LocalDate;

/**
 * One kind of Task that need to be done in the date.
 */
public class Event extends Task {

    private final LocalDate date;

    /**
     * Creates an event with description and date of event.
     *
     * @param description description of the event.
     * @param date date of event.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.date + ")";
    }
}
