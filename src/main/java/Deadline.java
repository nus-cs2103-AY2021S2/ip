/**
 * {@code Deadline} is a Task that has to be done before a specific date/time.
 *
 * @see Task
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Constructs a new uncompleted {@code Deadline}.
     *
     * @param description The name of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts {@code Deadline} into string format to be stored in file.
     *
     * @return String format to be stored in file.
     */
    public String encode() {
        return String.format("D / %s / %s", super.encode(), by);
    }

    /**
     * String representation of the Deadline.
     *
     * @return Deadline in check list form.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
