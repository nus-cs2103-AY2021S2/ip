package vergil.types;

import java.time.LocalDateTime;

/**
 * Represents a task with a specified date-and-time (i.e., a timed task).
 */
public abstract class TimedTask extends Task {
    private LocalDateTime dateTime;

    /**
     * Creates a new timed task that is unfinished.
     *
     * @param   description a description of the task.
     * @param   dateTime    the date-and-time of the task.
     */
    public TimedTask(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Creates a new timed task with its completion status specifed.
     *
     * @param   description a description of the task.
     * @param   dateTime    the date-and-time of the task.
     * @param   isDone      the completion status of the task.
     */
    public TimedTask(String description, LocalDateTime dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    public LocalDateTime getTime() {
        return dateTime;
    }
}
