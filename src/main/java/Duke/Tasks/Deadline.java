package Duke.Tasks;

/**
 * A subclass of task that has an extra variable to keep track of date and overrides toString() method of
 * <code>Duke.Tasks.Task</code>
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
