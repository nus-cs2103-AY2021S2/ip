package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 *
 * @author Benedict Khoo
 */
public class Deadline extends Task {
    public static final String INPUT_DATE_FORMAT = "yyyy-MM-dd";
    public static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);
    public static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final String TYPE = "D";
    private static final String SEPARATOR = "\\|";
    private static final int FIELD_COUNT = 4;

    /**
     * The deadline date.
     */
    protected LocalDate date;

    /**
     * Constructs a Deadline with the given description and due date.
     *
     * @param description The task description.
     * @param date        The due date.
     */
    public Deadline(String description, LocalDate date) {
        this(description, date, false);
    }

    /**
     * Constructs a Deadline with the given description, due date and done status.
     *
     * @param description The task description.
     * @param date        The due date.
     * @param isDone      The done status.
     */
    public Deadline(String description, LocalDate date, boolean isDone) {
        super(description, TYPE, isDone);
        this.date = date;
    }

    /**
     * Attempts to parse the given string as a Deadline. Returns the Deadline if successful.
     * Throws a TaskParseException if it fails.
     *
     * @param serialized The string to parse.
     * @return The parsed Deadline if successful.
     * @throws TaskParseException If parsing fails.
     */
    public static Deadline deserialize(String serialized) throws TaskParseException {
        final TaskParseException parseEx = new TaskParseException("Invalid deadline!");

        String[] fields = serialized.split(SEPARATOR);
        if (fields.length < FIELD_COUNT) {
            throw parseEx;
        }

        String type = fields[0];
        if (!type.equals(TYPE)) {
            throw parseEx;
        }

        boolean isDone = Boolean.parseBoolean(fields[1]);
        String description = fields[2];
        String dateStr = fields[3];
        LocalDate date;
        try {
            date = LocalDate.parse(dateStr, INPUT_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw parseEx;
        }

        return new Deadline(description, date, isDone);
    }

    /**
     * Returns true if the input string contains the type of a Deadline in the right place.
     * False otherwise.
     *
     * @param serialized The input string to test.
     * @return True if type matches. False otherwise.
     */
    public static boolean isDeadline(String serialized) {
        String[] fields = serialized.split(SEPARATOR);
        if (fields.length == 0) {
            return false;
        }

        String type = fields[0];
        return type.equals(TYPE);
    }

    /**
     * Returns the due date of the Deadline.
     *
     * @return The due date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the Deadline serialized as a string.
     *
     * @return The Deadline serialized as a string.
     */
    @Override
    public String serialize() {
        return String.format("%s|%b|%s|%s", getType(), isDone, getDescription(),
                date.format(INPUT_DATE_FORMATTER));
    }

    /**
     * Returns a string representation of the Deadline suitable for display to the user.
     *
     * @return A user-friendly representation of this Deadline.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", getType(), getStatusIcon(), getDescription(),
                date.format(OUTPUT_DATE_FORMATTER));
    }
}
