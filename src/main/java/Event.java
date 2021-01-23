import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final LocalDateTime date;

    public Event(String description, String date) throws InvalidFormatException, DateTimeParseException {
        super(description, "E");
        this.date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

        if(date.isEmpty())
            throw new InvalidFormatException("Please specify both task description and date/time using /at");
    }

    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM d YYYY");
        return "[" + super.getType() + "]" + super.toString() + " (by: " + df.format(this.date) + ")";
    }
}
