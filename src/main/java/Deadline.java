import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
    private final LocalDateTime by;

    /**
     * Initializes a Deadline Task with the specified description and dateTime.
     * @param description Description of the Deadline Task.
     * @param dateTime Date and Time the Deadline Task should be done by.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description, "D");
        by = dateTime;
    }

    public String toString() {
        return super.toString() + " (by: " + by.format(DATE_TIME_FORMAT) + ")";
    }

    public String toLog() {
        return super.toLog() + " | " + by;
    }
}
