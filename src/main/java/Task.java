public class Task {
    protected String taskDescription;
    protected boolean done;
    public static final String TASK_DELIMITER = " | ";

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        done = false;
    }

    public void setDone() {
        done = true;
    }

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "]" + TASK_DELIMITER + taskDescription;
    }
}
