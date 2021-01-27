package duke.task;

public class Event extends Task {
    private static final String TYPE = "E";
    private static final String SEPARATOR = "\\|";
    private static final int FIELD_COUNT = 4;

    protected String period;

    public Event(String description, String period) {
        this(description, period, false);
    }

    public Event(String description, String period, boolean isDone) {
        super(description, TYPE, isDone);
        this.period = period;
    }

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

    public static boolean isEvent(String serialized) {
        String[] fields = serialized.split(SEPARATOR);
        if (fields.length > 0) {
            String type = fields[0];
            return type.equals(TYPE);
        }
        return false;
    }

    public String getPeriod() {
        return period;
    }

    @Override
    public String serialize() {
        return String.format("%s|%b|%s|%s", getType(), isDone, getDescription(), period);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", getType(), getStatusIcon(), getDescription(), period);
    }
}
