package yoda.task;

/**
 * Deadline class that is a specialized version of the Task class and extends the Task class.
 */
public class Deadline extends Task {
    /**
     * Creates a Deadline object.
     * @param description Description of Deadline object.
     * @param by Datetime the Deadline object is due.
     */
    public Deadline(String description, String by) {
        super(description);
        setDateTime(by);
    }

    /**
     * Formats the Deadline information to be user-readable.
     * @return User-readable format of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " ---> by " + formatDateTime();
    }
}
