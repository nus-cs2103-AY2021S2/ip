import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline specified in the by argument.
 * If deadline is parsable as a LocalDateTime or LocalDate, it will be stored in the variable byDateTime or byDate.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDateTime byDateTime;
    protected LocalDate byDate;

    /**
     * Creates a task with a deadline specified in the by argument.
     * Tries to parse "by" in various DateTimeFormatter patterns and stores as LocalDateTime object in byDateTime,
     * or store as LocalDate object in byDate.
     *
     * @param description Describes the task.
     * @param by          Specifies the deadline of this task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        try {
            byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));
        } catch (DateTimeParseException ignored) {
        }
        try {
            byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm"));
        } catch (DateTimeParseException ignored) {
        }
        try {
            byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy kkmm"));
        } catch (DateTimeParseException ignored) {
        }
        try {
            byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException ignored) {
        }
        try {
            byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException ignored) {
        }
    }

    @Override
    public String toString() {
        if (byDateTime == null && byDate == null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else if (byDateTime != null) {
            return "[D]" + super.toString() +
                    " (by: " + byDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, ha")) + ")";
        } else {
            return "[D]" + super.toString() +
                    " (by: " + byDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
        }
    }
}
