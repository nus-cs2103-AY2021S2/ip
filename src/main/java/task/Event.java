package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task{
    protected LocalDateTime at;
    public static String parseFormat = "dd-MM-yyyy, hh:mma";
    public static String outputFormat = "dd MMM yyyy, hh:mma";
    /**
     * Instantiates a new event task.
     *
     * @param description the event description
     */
    public Event(String description, String at) {
        super(description);
        DateTimeFormatter format = DateTimeFormatter.ofPattern(parseFormat, Locale.US);
        this.at = LocalDateTime.parse(at, format);
    }

    public String getAt() {
        return at.format(DateTimeFormatter.ofPattern(outputFormat));
    }

    @Override
    public String toString() {
        return "EVENT \u00BB " + super.description + " (at: " + getAt() + ") " + super.getStatusIcon();
    }
}
