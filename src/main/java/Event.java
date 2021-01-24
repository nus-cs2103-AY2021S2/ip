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

    public String fileFormat() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        return "E | " + (super.isDone ? "1 | " : "0 | ") + this.description + " | " + df.format(this.date);
    }

    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        return "[" + super.getType() + "]" + super.toString() + " (at: " + df.format(this.date) + ")";
    }
}
