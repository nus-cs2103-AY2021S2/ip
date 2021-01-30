package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task class (with time formatting feature).
 */
public class Deadline extends Task {
    private final LocalDate time;

    public Deadline(String name, TaskType type, LocalDate time) {
        super(name, type);
        this.time = time;
    }

    public Deadline(String name, TaskType type, LocalDate time, boolean done) {
        super(name, type, done);
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
        return super.toString() + " (by: "
                + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
