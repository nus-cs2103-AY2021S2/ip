package duke;

/**
 * The Task class encapsulates
 * task descriptions and their completion status
 *
 * @author  Justin Gnoh
 * @version 1.0
 * @since   2021-02-06
 */
public class Task {
    protected boolean isDone;
    private String name;

    /**
     * Creates a Task with specified description.
     *
     * @param name This is the Task description
     */
    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    /**
     * Creates a Task with specified description and
     * completion status
     *
     * @param name This is the Task description
     * @param isDone This is the Task completion status
     */
    public Task(String name, Boolean isDone) {
        this.isDone = isDone;
        this.name = name;
    }

    /**
     * This method marks a task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * This method gets the current task completion status
     * and returns a symbol accordingly
     *
     * @return String This is a tick or a cross
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + name;
    }
}
