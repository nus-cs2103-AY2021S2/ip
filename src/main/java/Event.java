import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;
    protected DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + at.format(formatter) + ")";
    }
}
