public class Task {
    /**
     * Task class stores a String as name of the task and boolean indicating task completion
     */

    private String taskName;
    private boolean done;

    public Task(String name) {
        this.taskName = name;
        this.done = false;
    }

    public Task(String name, boolean isDone) {
        this.taskName = name;
        this.done = isDone;
    }

    public void markAsDone() {
        done = true;
    }

    public boolean isDone() {
        return done;
    }

    public String getTaskName() {
        return taskName;
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
