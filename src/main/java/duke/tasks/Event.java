package duke.tasks;

/**
 * Event class that extends from Task parent class
 * Event class specifies an event happening at a specific date and time.
 */
public class Event extends Task {

    protected String date;

    /**
     * Constructor method
     * @param description The description of the event.
     * @param date The event date.
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
