package duke.task;

import java.time.LocalDate;

/**
 * A deadline is a task with a date it must be done /by
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor to initialise a deadline's description and by
     * @param description the deadline's description
     * @param by when the task must be done /by
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.type = "deadline";
    }

    /**
     * Gets the date that the task must be done /by
     * @return the date the task must be done /by
     */
    public String getBy() {
        return this.by.toString();
    }

    /**
     * Overridden toString() method to include the type of the task
     * @return a string with the details of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format("(by: %s %s %s)",
                        this.by.getMonth(), this.by.getDayOfMonth(), this.by.getYear());
    }
}
