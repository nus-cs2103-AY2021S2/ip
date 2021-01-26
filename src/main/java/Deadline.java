/**
 * An implementation of the Task class that represents Deadline Tasks.
 * <p>
 * Deadline tasks are tasks that take a description, track whether they are done or not,
 * and take a /by parameter, specifying the Deadline time of the Deadline.
 * <p>
 * The Deadline class is visually represented with the prefix: [D]
 *
 * @author Douglas Wei Jing Allwood
 * @author douglas_allwood@u.nus.edu
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
