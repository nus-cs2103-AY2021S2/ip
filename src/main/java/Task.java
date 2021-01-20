import java.util.*;

class Task {
    private boolean done;
    private String taskName;

    public Task(String taskName) {
        this.done = false;
        this.taskName = taskName;
    }

    public void markAsDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}