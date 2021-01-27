import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A class that extends the Task class. An Event object corresponds to a Task object which has a description
 * event date and time.
 */
public class Event extends Task {
    LocalDate at;
    LocalTime time;

    public Event(String description, LocalDate at, LocalTime time) {
        super(description);
        this.at = at;
        this.time = time;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + " " + time + ")";
    }
}
