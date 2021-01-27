package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Event.
 */
public class Event extends Task{
    /**
     * Datetime of the task event.
     */
    protected LocalDateTime at;
    /**
     * The constant parseFormat.
     */
    public static String parseFormat = "dd/MM/yyyy, hh:mma";
    /**
     * The constant outputFormat.
     */
    public static String outputFormat = "dd MMM yyyy, hh:mma";

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
