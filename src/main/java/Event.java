import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate datetime;

    public Event(String description, LocalDate time) {
        super(description);
        this.datetime = time;
    }

    @Override
    public String toString() {
        String datetimeString = datetime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return String.format("[E][%s] %s (on: %s)", getStatus(), description, datetimeString);
    }
}