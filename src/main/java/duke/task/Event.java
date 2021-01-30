package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Event class to which represents a event task.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructs Event.
     *
     * @param isDone      Determines if task is completed.
     * @param description Task description.
     * @param at          Date of Event.
     */
    public Event(int isDone, String description, LocalDate at) {
        super('E', isDone, description);
        this.at = at;
    }

    /**
     * Creates String to be stored in the data file.
     *
     * @return String in the format to be stored in data file.
     */
    @Override
    public String getFileString() {
        return super.getFileString() + " // " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
