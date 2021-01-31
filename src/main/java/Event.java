import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate time;

    public Event(String description, String time) {
        super(description);
        this.time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                this.getStatusIcon(),
                this.description,
                this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
