package task;

import java.time.LocalDate;

/**
 * Event tasks
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Make event task
     * @param description
     * @param at
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
