public class Task {
    /**
     * Task class stores a String as name of the task and boolean indicating task completion
     */

    private final String taskName;
    private boolean isDone;

    public Task(String name) {
        this.taskName = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.taskName = name;
        this.isDone = isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public boolean isTaskDone() {
        return isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}
