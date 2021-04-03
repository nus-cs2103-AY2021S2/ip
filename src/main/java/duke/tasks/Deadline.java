package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadlines are tasks that need to be done before a specific date/time.
 * Inherited from Task, used to store information related to tasks of type 'deadline'.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline class object.
     *
     * @param description deadline description.
     * @param by a string that represents timing to be completed by.
     *           Currently, only "yyyy-mm-dd" format is supported.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for Deadline class object, used for storage.
     *
     * @param description deadline description.
     * @param isDone if the deadline is done.
     * @param by a string that represents timing to be completed by.
     *           Currently, only "yyyy-mm-dd" format is supported.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Returns the details of the deadline.
     *
     * @return the details of the deadline.
     */
    @Override
    public String toString() {
        assert by != null : "deadline does not have an by date!";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Gets the details of the deadline to be stored.
     *
     * @return the details of the deadline to be stored.
     */
    @Override
    public String getTaskInfoToStore() {
        assert !description.isEmpty() : "deadline does not have a description!";
        assert by != null : "deadline does not have an by date!";

        String divider = " | ";
        return "D" + divider
                + (isDone ? "1" : "0") + divider
                + description + divider
                + by;
    }

    /**
     * Updates the by date of the deadline.
     *
     * @param newDate new date to be updated.
     */
    @Override
    public void updateDate(LocalDate newDate) {
        this.by = newDate;
    }
}
