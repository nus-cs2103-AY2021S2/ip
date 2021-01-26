package duke.task;

import java.time.LocalDate;

/**
 * An Event is an instance of a task happening /at a particular date
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructor to initialise an event task
     * @param description description of the event
     * @param at date that the event takes place
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
        this.type = "event";
    }

    /**
     * Gets the date the event occurs
     * @return the date of the event
     */
    public String getAt() {
        return this.at.toString();
    }

    /**
     * Overridden toString() method which includes the type of the task
     * @return string of the details of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format("(at: %s %s %s)",
                this.at.getMonth(), this.at.getDayOfMonth(), this.at.getYear());
    }
}
