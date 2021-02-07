import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public Event(String message, String date) throws DateTimeParseException {
        super("E", message, LocalDate.parse(date));
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
