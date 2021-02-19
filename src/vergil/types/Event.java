package vergil.types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with some time to be held at.
 */
public class Event extends TimedTask {
    /**
     * Constructs an new event task that is unfinished.
     *
     * @param description a description of the task.
     * @param time the time the task is scheduled to occur.
     */
    public Event(String description, LocalDateTime time) {
        super(description, time);
    }

    /**
     * Constructs an new event task with its completion status specified.
     *
     * @param description a description of the task.
     * @param time the time the task is scheduled to occur.
     * @param isDone the completion status of the task.
     */
    public Event(String description, LocalDateTime time, boolean isDone) {
        super(description, time, isDone);
    }

    /**
     * Returns the task in the form of a save-file entry.
     * <p>
     *     The save-file entry's format is as follows:
     *     e::TASK_DESCRIPTION::TASK_IS_DONE::TASK_EVENT_TIME
     * </p>
     *
     * @return  a string representing the task as a save-file entry.
     */
    @Override
    public String getSaveString() {
        return String.format("e::%s::%s", super.getSaveString(), getTime());
    }

    /**
     * Returns a string representation of the task.
     * <p>
     *     The string representation's format is as follows:
     *     [E][ ] TASK_DESCRIPTION (at: TASK_EVENT_TIME), or
     *     [E][X] TASK_DESCRIPTION (at: TASK_EVENT_TIME)
     *     The 'X' represents the task's completion status: 'X' if completed; ' ' otherwise.
     * </p>
     *
     * @return  a string representing the task.
     */
    @Override
    public String toString() {
        return String.format(
                "[E]%s (at: %s)",
                super.toString(),
                getTime().format(DateTimeFormatter.ofPattern("d MMM y @ h:mm a")));
    }
}
