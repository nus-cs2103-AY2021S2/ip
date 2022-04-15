package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class represents an Event.
 */
public class Event extends Task {
    private final LocalDateTime startTime;

    /**
     * Constructs an Event.
     * @param name The name of the Event task.
     * @param eventStartTime The starting time and date for an Event.
     */
    public Event(String name, LocalDateTime eventStartTime) {
        super(name);
        startTime = eventStartTime;
    }

    /**
     * Returns a string in the given format.
     * @return A string in the given format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + startTime.format(DateTimeFormatter.ofPattern("MMM dd yyy HH:mm")) + ")";
    }

    /**
     * Returns a string in the given format for storing in files.
     * @return A string in the given format for storing in files.
     */
    @Override
    public String toFileString() {
        return "E " + super.toFileString() + " | "
                + startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
