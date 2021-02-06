package duke.task;
/**
 * Basic task class from which the specific tasks are formed
 */
public class Task {
    String task;
    boolean done = false;

    Task(String task) {
        this.task = task;
    }

    public void markDone() {
        this.done = true;
    }

    public boolean isDone() {
        return this.done;
    }

    public String getTaskName() {
        return this.task;
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return "[X] " + this.task;
        } else {
            return "[0] " + this.task;
        }
    }
}
