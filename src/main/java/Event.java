import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = 'E';
    }

    public String getEventTime() {
        return this.at;
        this.at = LocalDate.parse(at);
    }

    public String getAt() {
        return at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getAt() + ")";
    }
}
