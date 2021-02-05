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

    /**
     * Mark the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns boolean of the completion of the task
     * @return isDone boolean
     */
    public boolean isTaskDone() {
        return isDone;
    }

    /**
     * Returns the name of the task as a string
     * @return taskName
     */
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
