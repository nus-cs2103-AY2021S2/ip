package todobeast.tasks;

public abstract class Task {
    protected String taskDescription;
    protected boolean isDone;
    public static final String TASK_DELIMITER = " | ";

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        isDone = false;
    }

    public Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public void setDone() {
        isDone = true;
    }

    abstract public String formatForStorage(String delimiter);
}
