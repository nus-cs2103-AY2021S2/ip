import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;
    protected LocalDate localAt;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.localAt = LocalDate.parse(at);
        this.type = 'E';
    }

    public String getAt() {
        return this.at;
    }
    public String getFormattedAt() {
        return localAt.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getFormattedAt() + ")";
    }
}
