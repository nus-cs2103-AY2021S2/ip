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

    public Deadline(String description, boolean isDone, String by) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String infoToStore() {
        String divider = " | ";
        return "D" +  divider
                + (isDone ? "1" : "0") + divider
                + description + divider
                + by;
    }
}
