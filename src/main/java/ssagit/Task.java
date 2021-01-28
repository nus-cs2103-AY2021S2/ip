package ssagit;

/**
 * Task class, since every task is a 'todoTask' currently, todoTask
 * uses this as a default class
 */
public class Task {

    String taskName;
    boolean isDone;
    // TaskType type;
    // String time;

    /**
     * Task class constructor
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
        // this.type = type;
        // this.time = time;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String toString() {
        if (isDone) return "[T][X] " + taskName;
        return "[T][ ] " + taskName;
    }
}
