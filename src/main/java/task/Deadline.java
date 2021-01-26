package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task{
    protected LocalDateTime by;
    public static String parseFormat = "dd/MM/yyyy, hh:mma";
    public static String outputFormat = "dd MMM yyyy, hh:mma";
    /**
     * Instantiates a new deadline task.
     *
     * @param description the deadline description
     * @param by          the by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String getSimpleBy() {
        return by.format(DateTimeFormatter.ofPattern(parseFormat));
    }

    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern(outputFormat));
    }

    @Override
    public String toString() {
        return "DEADLINE \u00BB " + super.description + " (by: " + getBy() + ") " + super.getStatusIcon();
    }
}
