package duke.main;

/**
 * Inherited from Task, used to store information related to tasks of type 'deadline'.
 *
 * Deadlines are tasks that need to be done before a specific date/time
 */
public class Deadline extends Task{
    protected String by;

    /**
     * Constructor for Deadline class object
     * @param description deadline description
     * @param by timing to be completed by
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
