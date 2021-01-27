package duke.commands;

import duke.tasks.Task;

/**
 * An implementation of the duke.tasks.Task class that represents duke.commands.Deadline Tasks.
 * <p>
 * duke.commands.Deadline tasks are tasks that take a description, track whether they are done or not,
 * and take a /by parameter, specifying the duke.commands.Deadline time of the duke.commands.Deadline.
 * <p>
 * The duke.commands.Deadline class is visually represented with the prefix: [D]
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

    /**
     * Returns the exact String required to Construct this Task.
     *
     * @return A String that can be used to construct an equivalent Task.
     */
    @Override
    public String getConstructorString() {
        return getDescription() + " /by " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
