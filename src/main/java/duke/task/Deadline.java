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
     * @param name the deadline name
     * @param time the deadline time
     */
    public Deadline(String name, LocalDate time) {
        super(name, TaskType.DEADLINE);
        this.time = time;
    }

    /**
     * Constructs a Deadline object with isDone status specified.
     *
     * @param name   the deadline name
     * @param time   the deadline time
     * @param isDone the status of the deadline
     */
    public Deadline(String name, LocalDate time, boolean isDone) {
        super(name, TaskType.DEADLINE, isDone);
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

    /**
     * Returns the string format of the deadline.
     *
     * @return the string format of the deadline
     */
    @Override
    public String toString() {
        return super.toString() + " (by: "
                + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
