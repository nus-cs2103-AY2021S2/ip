/**
 * Represents a possible Task to be added.
 */
public class Task {
    private final String name;
    private boolean isDone;
    private boolean onSnooze;

    /**
     * Constructor to be used by subclasses.
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.onSnooze = false;
    }

    /**
     * Constructor to be used when retrieving data from hard drive.
     * @param name Name of the task.
     * @param done If the task is done or not.
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.isDone = done;
    }

    /**
     * Getter for boolean isDone.
     * @return If this task is done or not.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Getter for boolean onSnooze.
     * @return Return true if the Task is on snooze.
     */
    public boolean isOnSnooze() {
        return this.onSnooze;
    }

    /**
     * Method to toggle Snooze state of Task.
     */
    public void toggleSnooze() {
        this.onSnooze = !this.onSnooze;
    }

    /**
     * Getter for the name of the Task.
     * @return The name of the Task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the status of the Task.
     * @return The status of the Task.
     */
    public String getStatus() {
        return "[" + (this.isDone ? "\u2713" : "\u2718") + "] " + this.name;
    }

    /**
     * Method to mark this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

}
