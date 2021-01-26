import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    private String formatDate() {
        return this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }


    @Override
    public String getSaveString() {
        return "E" + " | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.formatDate() + ")";
    }
}