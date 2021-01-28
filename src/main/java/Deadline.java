import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime by;
    protected final static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");

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
