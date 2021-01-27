package duke.tasks;

import duke.util.DateFormatter;

import java.time.LocalDate;

/**
 * Represents a Task with a Deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline.
     * @param description Description of the Task.
     * @param by Deadline of the Task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.taskType = "Deadline";
    }

    /**
     * Constructor for Deadline.
     * @param description Description of the Task.
     * @param isDone Marks whether the Task is Done.
     * @param by Deadline of the Task.
     */
    public Deadline(String description, Boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
        this.taskType = "Deadline";
    }

    /**
     * Returns the Deadline of the Task.
     * @return A LocalDate representing the Task Deadline.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Returns a String representation of the Task.
     * @return A String representation of the Task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateFormatter.decodeDate(by) + ")";
    }
}
