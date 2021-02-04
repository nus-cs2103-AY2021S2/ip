public class Deadline extends Task {

    protected String by;

    /**
     * Constructor to create a task with deadline.
     *
     * @param description task.
     * @param by time.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
