import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class Deadline extends Task {
    private static final DateTimeFormatter BY_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("dd/MM/yyyy")
            .appendPattern("[ HHmm]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    private final boolean hasTime;
    protected LocalDateTime by;

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDateTime.parse(by.trim(), BY_FORMATTER);
        this.hasTime = (by.trim().length() > 10);
    }

    @Override
    public String toFileString() {
        return String.format("%s | %s | %s\n", Command.DEADLINE, super.toFileString(), this.by.format(BY_FORMATTER));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = this.hasTime ? DATETIME_FORMATTER : DATE_FORMATTER;
        return "[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }
}
