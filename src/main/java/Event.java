import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected String atString;
    protected LocalDate atLocalDate;

    public Event(String description, String at) {
        super(description);
        atString = at;
        atLocalDate = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        String atDateFormatted = atLocalDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.doneToString() + " (at: " + atDateFormatted + ")";
    }

    public String getAt() {
        return atString;
    }
}