package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate time;

    /**
     * Creates an Event task with given description, isDone status, and time.
     * isDone is automatically set as False.
     *
     * @param description
     * @param time
     */
    public Event(String description, String time) {
        super(description);
        this.time = LocalDate.parse(time);
    }

    /**
     * Creates an Event task with given description, isDone status, and time.
     *
     * @param description
     * @param isDone
     * @param time
     */
    public Event(String description, boolean isDone, String time) {
        this(description, isDone, LocalDate.parse(time));
    }

    /**
     * Creates an Event task with given description, isDone status, and time.
     *
     * @param description
     * @param isDone
     * @param time
     */
    public Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Returns a string representation of Event
     *
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Returns a string representation of Event to be saved in data file
     *
     * @return String
     */
    public String toSavedString() {
        return String.format("E | %d | %s | %s", super.isDone ? 1 : 0, super.description, time);
    }

    public Task clone() {
        return new Event(description, isDone, time);
    }
}
