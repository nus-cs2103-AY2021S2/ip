public class Task {
    private Boolean isDone;
    private String taskInfo;

    public Task(String taskInfo) {
        isDone = false;
        this.taskInfo = taskInfo;
    }

    @Override
    public String toString() {
        String s = "";
        if (isDone) {
            s = "[X] ";
        } else {
            s = "[ ] ";
        }
        return s + taskInfo;
    }

    public void completed() {
        isDone = true;
    }
}
