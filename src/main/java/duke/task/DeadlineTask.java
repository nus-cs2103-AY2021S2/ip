package duke.task;

import java.time.LocalDate;

/**
 * Deadline tasks are tasks that have to be completed by a specified date.
 *
 * @author  Nicole Ang
 */
public class DeadlineTask extends Task {
    protected LocalDate by;

    /**
     * Constructs a new DeadlineTask object given a description and a deadline.
     *
     * @param description   Task description.
     * @param by    Date task needs to be completed by.
     */
    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task specifying its description and completion date.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
