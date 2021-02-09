package dukeproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event task where we can specific a task to be completed at a certain date time.
 * An event object corresponds to a task with a description and
 * a datetime for the event to be completed.
 *
 * X means that the event has been completed.
 */
public class Event extends Task {

    private final LocalDateTime eventDate;

    /**
     * Constructor for the Event task, specifying the description of the task and
     * the date time to be completed at.
     * @param description Description of the event task.
     * @param eventDate Date time for the event to be completed at.
     */
    public Event(String description, LocalDateTime eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    /**
     * Constructor for the Event task, specifying the description of the task,
     * the date time to be completed at and whether the task has been completed.
     * @param description Description of the event task.
     * @param eventDate Date time for the event to be completed at.
     * @param isDone Determine whether the task is done or not.
     */
    public Event(String description, LocalDateTime eventDate, boolean isDone) {
        super(description, isDone);
        this.eventDate = eventDate;
    }

    /**
     * Returns a readable description of the task.
     * E.g. [E][ ] Tutorial Class (at: 10 Feb 2021 - 1200)
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
            super.getStatusIcon(), super.toString(),
            eventDate.format(DateTimeFormatter.ofPattern("d MMM yyyy - HHmm")));
    }
}
