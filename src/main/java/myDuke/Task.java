package myDuke;

/**
 * Represents a Task that contains its information/description and a done/not-done status
 */
public class Task {
    String info;
    boolean isDone;

    Task(String s, boolean b) {
        this.info = s;
        this.isDone = b;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the current task object as done and returns it as a new Task object.
     * @returns new done Task object.
     */
    Task setAsDone() {
        return new Task(this.info, true);
    }

    /**
     * Marks the current task object as undone and returns it as a new Task object.
     * @returns new undone Task object.
     */
    Task setAsUndone() {
        return new Task(this.info, false);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.info;
    }
}