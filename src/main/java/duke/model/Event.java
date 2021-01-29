package duke.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event class denotes an event.
 */
public class Event extends Task {
    private final LocalDate dateTime;

    /**
     * Constructs an Event.
     * @param isCompleted   Checked if the task is completed.
     * @param taskName     The task name.
     * @param dateTime     The date and time of the event.
     */
    public Event(boolean isCompleted, String taskName, LocalDate dateTime) {
        super('E', isCompleted, taskName);
        this.dateTime = dateTime;
    }

    /**
     * Generate a string to store in a file.
     * @return   A string that will be store in a file.
     */
    @Override
    public String generateFileFormatString() {
        return super.generateFileFormatString() + " // "
                + this.dateTime;
    }

    /**
     * A string representation of an event.
     * @return  A string representing an event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
