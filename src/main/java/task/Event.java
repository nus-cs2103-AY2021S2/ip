package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    LocalDate eventTime;

    public Event(String taskName, LocalDate eventTime) {
        super(taskName);
        this.eventTime = eventTime;
    }

    public Event(String taskName, boolean done, LocalDate eventTime) {
        super(taskName, done);
        this.eventTime = eventTime;
    }

    public String toString() {
        String formattedDate = this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }
}
