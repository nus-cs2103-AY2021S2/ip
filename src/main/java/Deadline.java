import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public static final String INPUT_DATE_FORMAT = "yyyy-MM-dd";
    public static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);
    public static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final String TYPE = "D";
    private static final String SEPARATOR = "\\|";
    private static final int FIELD_COUNT = 4;

    protected LocalDate date;

    public Deadline(String description, LocalDate date) {
        this(description, date, false);
    }

    public Deadline(String description, LocalDate date, boolean isDone) {
        super(description, TYPE, isDone);
        this.date = date;
    }

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

    public static boolean isDeadline(String serialized) {
        String[] fields = serialized.split(SEPARATOR);
        if (fields.length > 0) {
            String type = fields[0];
            return type.equals(TYPE);
        }
        return false;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String serialize() {
        return String.format("%s|%b|%s|%s", getType(), isDone, getDescription(),
                date.format(INPUT_DATE_FORMATTER));
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", getType(), getStatusIcon(), getDescription(),
                date.format(OUTPUT_DATE_FORMATTER));
    }
}
