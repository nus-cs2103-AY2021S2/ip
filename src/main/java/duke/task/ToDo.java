package duke.task;

/**
 * Represents a simple todo task.
 *
 * @author Benedict Khoo
 */
public class ToDo extends Task {
    private static final String TYPE = "T";
    private static final String SEPARATOR = "\\|";
    private static final int FIELD_COUNT = 3;

    /**
     * Constructs a ToDo with the given description.
     *
     * @param description The task description.
     */
    public ToDo(String description) {
        this(description, false);
    }

    /**
     * Constructs a ToDo with the given description and done status.
     *
     * @param description The task description.
     * @param isDone      The done status.
     */
    public ToDo(String description, boolean isDone) {
        super(description, TYPE, isDone);
    }

    /**
     * Attempts to parse the given string as a ToDo. Returns the ToDo if successful.
     * Throws a TaskParseException if it fails.
     *
     * @param serialized The string to parse.
     * @return The parsed ToDo if successful.
     * @throws TaskParseException If parsing fails.
     */
    public static ToDo deserialize(String serialized) throws TaskParseException {
        final TaskParseException parseEx = new TaskParseException("Invalid ToDo!");

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

        return new ToDo(description, isDone);
    }

    /**
     * Returns true if the input string contains the type of a ToDo in the right place.
     * False otherwise.
     *
     * @param serialized The input string to test.
     * @return True if type matches. False otherwise.
     */
    public static boolean isToDo(String serialized) {
        String[] fields = serialized.split(SEPARATOR);
        if (fields.length == 0) {
            return false;
        }

        String type = fields[0];
        return type.equals(TYPE);
    }

    /**
     * Returns the ToDo serialized as a string.
     *
     * @return The ToDo serialized as a string.
     */
    @Override
    public String serialize() {
        return String.format("%s|%b|%s", getType(), isDone, getDescription());
    }

    /**
     * Returns a string representation of the ToDo suitable for display to the user.
     *
     * @return A user-friendly representation of this ToDo.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getType(), getStatusIcon(), getDescription());
    }
}
