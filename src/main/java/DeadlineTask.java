/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {

    protected String deadline;

    /**
     * Creates a new instance of <code>DeadlineTask</code>.
     * @param description Description of deadline task.
     */
    public DeadlineTask(String description, String deadlline) {
        super(description);
        this.deadline = deadlline;
    }

    /**
     * Returns String representation of deadline task.
     * @return String representation of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + this.description
                + " (by: " + this.deadline + ")";
    }
}
