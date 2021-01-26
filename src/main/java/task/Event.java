package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task{
    protected LocalDateTime at;
    public static String parseFormat = "dd/MM/yyyy, hh:mma";
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

    public String getSimpleAt() {
        return at.format(DateTimeFormatter.ofPattern(parseFormat));
    }

    public String getAt() {
        return at.format(DateTimeFormatter.ofPattern(outputFormat));
    }

    @Override
    public String toString() {
        return "EVENT \u00BB " + super.description + " (at: " + getAt() + ") " + super.getStatusIcon();
    }
}
