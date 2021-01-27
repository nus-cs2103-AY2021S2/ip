package duke.task;

public class ToDo extends Task {
    private static final String TYPE = "T";
    private static final String SEPARATOR = "\\|";
    private static final int FIELD_COUNT = 3;

    public ToDo(String description) {
        this(description, false);
    }

    public ToDo(String description, boolean isDone) {
        super(description, TYPE, isDone);
    }

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

    public static boolean isToDo(String serialized) {
        String[] fields = serialized.split(SEPARATOR);
        if (fields.length > 0) {
            String type = fields[0];
            return type.equals(TYPE);
        }
        return false;
    }

    @Override
    public String serialize() {
        return String.format("%s|%b|%s", getType(), isDone, getDescription());
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getType(), getStatusIcon(), getDescription());
    }
}
