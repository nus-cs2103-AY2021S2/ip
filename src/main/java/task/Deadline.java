package task;

/**
 * A task type deadline object.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Instantiates a new deadline task.
     *
     * @param description the deadline description
     * @param by          the by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets deadline by date.
     *
     * @return the by date
     */
    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "DEADLINE \u00BB " + super.description + " (by: " + by + ") " + super.getStatusIcon();
    }
}
