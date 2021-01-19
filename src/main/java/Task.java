public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task (String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void markDone () {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String status = this.isDone ? "[X]" : "[ ]";
        return status + " " + taskName;
    }
}
