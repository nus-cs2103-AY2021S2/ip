import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        String dateFormatted = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.doneToString() + " (at: " + dateFormatted + ")";
    }

    public String getAt() {
        return this.at;
    }
}