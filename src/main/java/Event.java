import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime date;

    public Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
        this.taskType = "Event";
    }

    @Override
    public String getTaskDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + getTaskDate() + ")";
    }
}
