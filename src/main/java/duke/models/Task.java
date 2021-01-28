package duke.models;

public abstract class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean getTaskDone() {
        return isDone;
    }

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
