/**
 * This class extends the Task class and is used to
 * represent a single item with a deadline that users can add to their list.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone, String description, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getDescription() {
        return "Deadline`" + this.isDone + "`" + this.description + "`" + this.by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (" + by + ")";
    }
}
