package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class extends the Task class to store information
 * about an event happening at a specific date.
 */
public class Event extends Task {
    private LocalDate time;

    /**
     * Construct an event.
     * @param task the task description
     * @param time time of the event
     */
    public Event(String task, String time) {
        super(task);
        this.time = LocalDate.parse(time);
    }

    /**
     * Overloaded constructor used for internal date manipulation.
     * @param task the task description
     * @param time time of the event
     */
    public Event(String task, LocalDate time) {
        super(task);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
