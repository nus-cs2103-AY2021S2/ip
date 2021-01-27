import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A class that extends the Task class. An Event object corresponds to a Task object which has a description
<<<<<<< HEAD
 * event date and time.
=======
 * event date, start and end time.
>>>>>>> branch-Level-9
 */
public class Event extends Task {
    LocalDate at;
    LocalTime start;
    LocalTime end;

    public Event(String description, LocalDate at, LocalTime start, LocalTime end) {
        super(description);
        this.at = at;
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + " " + start + "-" + end + ")";
    }
}
