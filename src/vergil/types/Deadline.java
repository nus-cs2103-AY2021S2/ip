package vergil.types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline to meet.
 */
public class Deadline extends TimedTask {
    /**
     * Constructs a new deadline task that is unfinished.
     *
     * @param   description a description of the deadline task.
     * @param   time        the time the task is due.
     */
    public Deadline(String description, LocalDateTime time) {
        super(description, time);
    }

    /**
     * Constructs a new deadline task with its completion status specified.
     *
     * @param   description a description of the task.
     * @param   time        the time the task is due.
     * @param   isDone      the completion status of the task.
     */
    public Deadline(String description, LocalDateTime time, boolean isDone) {
        super(description, time, isDone);
    }

    /**
     * Returns the task in the form of a save-file entry.
     * <p>
     *     The save-file entry's format is as follows:
     *     d::TASK_DESCRIPTION::TASK_IS_DONE::TASK_DEADLINE_TIME
     * </p>
     *
     * @return  a string representing the task as a save-file entry.
     */
    @Override
    public String getSaveString() {
        return String.format(
                "d::%s::%s",
                super.getSaveString(),
                getTime()
        );
    }

    /**
     * Returns a string representation of the task.
     * <p>
     *     The string representation's format is as follows:
     *     [D][ ] TASK_DESCRIPTION (by: TASK_DEADLINE_TIME), or
     *     [D][X] TASK_DESCRIPTION (by: TASK_DEADLINE_TIME)
     *     The 'X' represents the task's completion status: 'X' if completed; ' ' otherwise.
     * </p>
     *
     * @return  a string representing the task.
     */
    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                getTime().format(DateTimeFormatter.ofPattern("d MMM y @ h:mm a")));
    }
}
