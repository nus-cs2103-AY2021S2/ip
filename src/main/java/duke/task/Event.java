package duke.task;

/**
 * Represents a task with a period/duration.
 */
public class Event extends Task {
    private static final String TYPE = "E";
    private static final String SEPARATOR = "\\|";
    private static final int FIELD_COUNT = 4;

    /**
     * The period of this event.
     */
    protected String period;

    /**
     * Constructs an Event with the given description and period.
     *
     * @param description The task description.
     * @param period The period during which the event takes place.
     */
    public Event(String description, String period) {
        this(description, period, false);
    }

    /**
     * Constructs an Event with the given description, period and done status.
     *
     * @param description The task description.
     * @param period The period during which the event takes place.
     * @param isDone The done status of this event.
     */
    public Event(String description, String period, boolean isDone) {
        super(description, TYPE, isDone);
        this.period = period;
    }

    /**
     * Attempts to parse the given string as an Event. Returns the Event if successful.
     * Throws a TaskParseException if it fails.
     *
     * @param serialized The string to parse.
     * @return The parsed Event if successful.
     * @throws TaskParseException If parsing fails.
     */
    public static Event deserialize(String serialized) throws TaskParseException {
        final TaskParseException parseEx = new TaskParseException("Invalid event!");

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
        String period = fields[3];

        return new Event(description, period, isDone);
    }

    /**
     * Returns true if the input string contains the type of an Event in the right place.
     * False otherwise.
     *
     * @param serialized The input string to test.
     * @return True if type matches. False otherwise.
     */
    public static boolean isEvent(String serialized) {
        String[] fields = serialized.split(SEPARATOR);
        if (fields.length > 0) {
            String type = fields[0];
            return type.equals(TYPE);
        }
        return false;
    }

    /**
     * Returns the period of this event.
     *
     * @return The period.
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Returns the Event serialized as a string.
     *
     * @return The Event serialized as a string.
     */
    @Override
    public String serialize() {
        return String.format("%s|%b|%s|%s", getType(), isDone, getDescription(), period);
    }

    /**
     * Returns a string representation of the Event suitable for display to the user.
     *
     * @return A user-friendly representation of this Event.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", getType(), getStatusIcon(), getDescription(), period);
    }
}
