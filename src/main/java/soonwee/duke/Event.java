package soonwee.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

<<<<<<< Updated upstream
public class Event extends Task{
=======
/**
 * Represents an Event instance. An Event instance will store the task
 * name and its starting time.
 */
public class Event extends Task {
>>>>>>> Stashed changes

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
