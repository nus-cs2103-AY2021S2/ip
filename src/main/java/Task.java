public abstract class Task {
    protected Boolean isDone;
    protected String taskInfo;

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

    public void completeTask() {
        isDone = true;
    }

    public abstract String getData();
}
