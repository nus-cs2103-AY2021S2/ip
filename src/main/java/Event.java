import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime at;
    protected final static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");

    public Event(String description, LocalDateTime dateTime) {
        super(description, "E");
        at = dateTime;
    }

    public String toString() {
        return super.toString() + " (at: " + at.format(DATE_TIME_FORMAT) + ")";
    }

    public String toLog() {
        return super.toLog() + " | " + at;
    }
}
