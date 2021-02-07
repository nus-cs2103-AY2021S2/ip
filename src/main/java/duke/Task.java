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
    public String name;

    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + name;
    }
}
