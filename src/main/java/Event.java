import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a Task object defined by a time at which the event will occur
 */
public class Event extends Task implements DueDate {
    protected LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    public Event markAsDone() {
        return new Event(this.description, true, this.time);
    }

    @Override
    public String getDueDate() {
        return this.time.toString();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (at: " + time.format(formatter) + ")";
    }
}
