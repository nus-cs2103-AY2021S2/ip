import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {
    LocalDate at;
    LocalTime time;

    public Event(String description, LocalDate at, LocalTime time) {
        super(description);
        this.at = at;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + " " + time + ")";
    }
}
