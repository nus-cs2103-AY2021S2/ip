import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate datetime;

    public Event(String description, LocalDate time) {
        super(description);
        this.datetime = time;
    }

    public String getSaveString() {
        String datetimeString = datetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (this.isDone()) {
            return String.format("event [isDone] %s /on %s\n", description, datetimeString);
        } else {
            return String.format("event %s /on %s\n", description, datetimeString);
        }
    }

    @Override
    public String toString() {
        String datetimeString = datetime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return String.format("[E][%s] %s (on: %s)", getStatus(), description, datetimeString);
    }
}