import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Command {
    public LocalDate eventDate;

    public Event(String commandDescription, LocalDate eventTime) {
        super(commandDescription);
        this.isDone = false;
        this.eventDate = eventTime;
    }

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
    String formattedDate = eventDate.format(format);

    @Override
    public String toString() {
        return "[E] " + super.toString() + " | at: " + formattedDate;
    }
}