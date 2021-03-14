package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event.
 */
public class Event extends Task {
    /**
     * The constant parseFormat.
     */
    private static final String parseFormat = "dd/MM/yyyy, hh:mma";
    /**
     * The constant outputFormat.
     */
    private static final String outputFormat = "dd MMM yyyy, hh:mma";
    /**
     * Datetime of the task event.
     */
    protected LocalDateTime at;

    /**
     * Instantiates a new event task.
     *
     * @param description the event description
     * @param at          the at
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Gets the simple localdatetime parsed format.
     *
     * @return the simple at
     */
    public String getSimpleAt() {
        return at.format(DateTimeFormatter.ofPattern(parseFormat));
    }

    /**
     * Gets the intended localdatetime output format.
     *
     * @return the at
     */
    public String getAt() {
        return at.format(DateTimeFormatter.ofPattern(outputFormat));
    }

    @Override
    public String toString() {
        return "EVENT \u00BB " + super.description + " (at: " + getAt() + ") " + super.getStatusIcon();
    }
}
