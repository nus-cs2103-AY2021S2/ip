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

    public boolean getStatus() {
        return this.done;
    }

    public String getTaskName() {
        return this.task;
    }

    @Override
    public String toString() {
        if (this.getStatus()) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}
