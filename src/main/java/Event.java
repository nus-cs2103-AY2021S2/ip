import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected final String time;
    protected LocalDate date;

    public Event (String description, String time) {
        super(description);
        this.time = time;
        date = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
