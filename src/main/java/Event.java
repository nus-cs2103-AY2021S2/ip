import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate d;

    public Event(String description, String at) {
        super(description);
        this.d = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                this.d.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
