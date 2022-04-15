package task;

/**
 * Task to represent an objective for the user to complete.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task objective with the supplied description. Tasks created
     * this way are marked as isDone = false by default.
     *
     * @param desc String description of the Task.
     */
    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    /**
     * Overloaded constructor to create a Task with the supplied description and
     * completion status.
     *
     * @param desc String description of the Task.
     * @param isDone True, if the Task should be initialised as done.
     */
    public Task(String desc, boolean isDone) {
        this.description = desc;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns a tick if the task is completed and a blank character if not.
     *
     * @return Tick, if done and space if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return blank or tick symbol
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract TaskType getTaskType();

    public boolean isDone() {
        return isDone;
    }

    public abstract String getCommandString();

    /**
     * Convert the object, its state and variables to a String representation
     * that can be parsed to obtain back the same object. The implementing class
     * should also have a static fromSaveString(String) method to reconstruct
     * the object from the saveString.
     *
     * @return String representation of the object and its state.
     */
    public abstract String toSaveString();
}
