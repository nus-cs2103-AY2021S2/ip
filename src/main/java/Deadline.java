import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class Deadline extends Task {
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String TIME_FORMAT = "[ HHmm]";
    private static final DateTimeFormatter BY_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern(DATE_FORMAT)
            .appendPattern(TIME_FORMAT)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    protected LocalDateTime by;
    private final boolean hasTime;

    /**
     * Constructor for the Deadline class.
     *
     * @param description Description of the Deadline task.
     * @param by Due date of the Deadline task.
     * @throws DateTimeParseException If an invalid "by" date is provided.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDateTime.parse(by.trim(), BY_FORMATTER);
        this.hasTime = (by.trim().length() > DATE_FORMAT.length());
    }

    /**
     * Constructor for the Deadline class.
     *
     * @param description Description of the Deadline task.
     * @param by Due date of the Deadline task.
     * @param tags String of tags.
     * @throws DateTimeParseException If an invalid "by" date is provided.
     */
    public Deadline(String description, String by, String tags) throws DateTimeParseException {
        super(description, tags);
        this.by = LocalDateTime.parse(by.trim(), BY_FORMATTER);
        this.hasTime = (by.trim().length() > DATE_FORMAT.length());
    }

    @Override
    public String toFileString() {
        return String.format("%s | %s | %s\n", Command.DEADLINE, super.toFileString(), this.by.format(BY_FORMATTER));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = this.hasTime ? DATETIME_FORMATTER : DATE_FORMATTER;
        return String.format("[D]%s (by: %s)%s",
                super.toStringWithoutTags(), this.by.format(formatter), super.getTagsString());
    }
}
