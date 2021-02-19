import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Command {
    public LocalDate eventDate;
    private String formattedDate;

    public Event(String commandDescription, LocalDate eventDate) {
        super(commandDescription);
        this.isDone = false;
        this.eventDate = eventDate;

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
        this.formattedDate = this.eventDate.format(format);
    }

    public String getTime() {
        return this.eventTime;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " | at: " + formattedDate;
    }
}