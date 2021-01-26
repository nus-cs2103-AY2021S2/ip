import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
    }
}
