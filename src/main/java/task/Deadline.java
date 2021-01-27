package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline.
 */
public class Deadline extends Task{
    /**
     * Datetime of the task deadline.
     */
    protected LocalDateTime by;
    /**
     * The constant parseFormat.
     */
    public static String parseFormat = "dd/MM/yyyy, hh:mma";
    /**
     * The constant outputFormat.
     */
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

    /**
     * Gets the simple localdatetime parse format.
     *
     * @return the simple by
     */
    public String getSimpleBy() {
        return by.format(DateTimeFormatter.ofPattern(parseFormat));
    }

    /**
     * Gets the intended localdatetime output format.
     *
     * @return the by
     */
    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern(outputFormat));
    }

    @Override
    public String toString() {
        return "DEADLINE \u00BB " + super.description + " (by: " + getBy() + ") " + super.getStatusIcon();
    }
}
