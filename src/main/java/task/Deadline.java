package task;

public class Deadline extends Task{
    protected String by;
    /**
     * Instantiates a new deadline task.
     *
     * @param description the deadline description
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "DEADLINE \u00BB " + super.description + " (by: " + by + ") " + super.getStatusIcon();
    }
}
