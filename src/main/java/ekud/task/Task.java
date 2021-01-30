package ekud.task;

import java.util.LinkedList;

/**
 * The abstract base class for all kinds of tasks
 */
public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    /**
     * Construct a new Task.
     *
     * @param description The task description
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Check if the Task is marked as completed.
     *
     * @return true if markAsDone() has been invoked previously, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Mark the Task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Retrieve the appropriate status icon based on the done status of the Task.
     *
     * @return A tick symbol if Task is done, a cross symbol otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or cross symbols
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Consolidate all information necessary to reconstruct the exact same Task.
     *
     * @return String LinkedList containing {isDone, description}.
     */
    public LinkedList<String> export() {
        LinkedList<String> list = new LinkedList<>();
        list.add(String.valueOf(isDone));
        list.add(description);
        return list;
    }
}
