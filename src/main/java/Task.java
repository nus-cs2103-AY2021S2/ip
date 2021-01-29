public class Task {
    protected String taskDescription;
    protected boolean isDone;
    public static final String TASK_DELIMITER = " | ";
    public static final String TASK_DELIMITER_REGEX = " [|] ";

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

    @Override
    public String toString() {
        return (isDone ? "done" : " ") + TASK_DELIMITER + taskDescription;
    }
}
