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
     * @param type type Event
     * @param time event time
     */
    public Event(String name, TaskType type, LocalDate time) {
        super(name, type);
        this.time = time;
    }

    /**
     * Constructs a Event object with isDone status specified.
     *
     * @param name   event name
     * @param type   type Event
     * @param time   event time
     * @param isDone the status of the event
     */
    public Event(String name, TaskType type, LocalDate time, boolean isDone) {
        super(name, type, isDone);
        this.time = time;
    }

    /**
     * Returns the time of the deadline.
     *
     * @return the deadline time to be returned
     */
    public LocalDate getTime() {
        return time;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: "
                + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
