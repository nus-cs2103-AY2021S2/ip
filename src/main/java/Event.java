import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public LocalDate at;

    public Event(String description, String at) {
        super(description, "Event");
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date entered!");
        }
    }

    public String getAt() {
        return at.toString();
    }

    @Override
    public String toString() {
        String formatAt = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[E]" + super.toString() + "(at: " + formatAt + ")";
    }
}
