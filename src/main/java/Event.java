import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {
    String at;
    String time;

    public Event(String description, String at, String time) {
        super(description);
        this.at = at;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + " " + time + ")";
    }
}
