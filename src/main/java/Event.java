import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    LocalDate dateTime;

    public Event(String taskName, LocalDate dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
