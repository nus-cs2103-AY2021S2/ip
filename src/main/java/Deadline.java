
/**
 * Encapsulates information and state of a Deadline.
 * For tasks that must be ccompletely by a certain time.
 */
public class Deadline extends Task {
    /** Deadline of task. */
    protected String by;

    /**
     * Initialises a new Deadline with text description.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", 
            this.getStatusIcon(), super.toString(), this.by);
        //return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
