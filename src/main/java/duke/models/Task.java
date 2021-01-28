package duke.models;

/**
 * Abstract class of all Tasks.
 */
public abstract class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the task's name.
     * @return task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns whether the task has been marked done.
     * @return whether the task has been marked done
     */
    public boolean getTaskDone() {
        return isDone;
    }

    /**
     * Return the string representation of task.
     * @return string representation of task
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        builder.append(isDone ? 'X' : ' ');
        builder.append("] ");
        builder.append(taskName);
        return builder.toString();
    }
}
