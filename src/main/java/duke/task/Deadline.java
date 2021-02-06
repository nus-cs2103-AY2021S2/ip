package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task class (with time formatting feature).
 */
public class Deadline extends Task {
    private final LocalDate time;

    /**
     * Constructs a Deadline object.
     *
     * @param name deadline name
     * @param type type Deadline
     * @param time deadline time
     */
    public Deadline(String name, TaskType type, LocalDate time) {
        super(name, type);
        this.time = time;
    }

    /**
     * Constructs a Deadline object with isDone status specified.
     *
     * @param name   deadline name
     * @param type   type Deadline
     * @param time   deadline time
     * @param isDone the status of the deadline
     */
    public Deadline(String name, TaskType type, LocalDate time, boolean isDone) {
        super(name, type, isDone);
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
        return super.toString() + " (by: "
                + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
