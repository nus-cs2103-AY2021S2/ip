package duke.task;

/**
 * A class represents a Task.
 */
public class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructs a Task.
     * @param taskName The name of the Task.
     */
    protected Task(String taskName) {
        name = taskName;
        isDone = false;
    }

    /**
     * Marks the status of the Task as "completed".
     */
    public void complete() {
        isDone = true;
    }

    /**
     * Returns the status icon of the Task.
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Returns a string in the given format.
     * @return A string in the given format.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }

    /**
     * Returns a string in the given format for storing in files.
     * @return A string in the given format for storing in files.
     */
    public String toFileString() {
        return "| " + (isDone ? "1" : "0") + " | " + name;
    }
}
