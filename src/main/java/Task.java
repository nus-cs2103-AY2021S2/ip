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

    public void markAsDone() {
        done = true;
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
