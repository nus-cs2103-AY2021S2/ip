package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Event class to which represents a event task.
 */
public class Event extends Task {
    protected LocalDate date;

    /**
     * Constructs Event.
     *
     * @param isDone      Determines if task is completed.
     * @param description Task description.
     * @param date        Date of Event.
     */
    public Event(int isDone, String description, LocalDate date) {
        super('E', isDone, description);
        this.date = date;
    }

    /**
     * Creates String to be stored in the data file.
     *
     * @return String in the format to be stored in data file.
     */
    @Override
    public String getFileString() {
        return super.getFileString() + " // " + this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
