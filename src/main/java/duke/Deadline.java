package duke;

/**
 * A subclass of Task, used for tasks with specified timing.
 */
public class Deadline extends Task {
    protected static final String TAG = "[D]";

    public Deadline(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return Deadline.TAG + super.toString();
    }
}
