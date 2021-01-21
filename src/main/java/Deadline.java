public class Deadline extends AbstractTask {
    protected String by;

    /*
     * Constructs a deadline task using the description and the deadline timing
     */
    public Deadline(String description, String by) throws DukeEmptyDescriptionException {
        super(description);
        this.by = by;
    }

    /*
     * Return a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}