package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event task class (with time formatting feature).
 */
public class Event extends Task {
    private final LocalDate time;

    /**
     * Constructs a Event object.
     *
     * @param name event name
     * @param time event time
     */
    public Event(String name, LocalDate time) {
        super(name, TaskType.EVENT);
        this.time = time;
    }

    /**
     * Constructs a Event object with isDone status specified.
     *
     * @param name   event name
     * @param time   event time
     * @param isDone the status of the event
     */
    public Event(String name, LocalDate time, boolean isDone) {
        super(name, TaskType.EVENT, isDone);
        this.time = time;
    }

    /**
     * Returns the time of the deadline.
     *
     * @return the deadline time to be returned
     */
    @Override
    public LocalDate getTime() {
        return time;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: "
                + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
