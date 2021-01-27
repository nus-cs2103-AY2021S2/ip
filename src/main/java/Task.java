public class Task {
    public String task;
    public boolean isDone;

    public Task(String task) {
        task = task.strip();
        this.task = task;
        this.isDone = false;
    }

    public Task(String task, boolean isDone) {
        task = task.strip();
        this.task = task;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + task;
    }

    public void doneTask() {
        isDone = true;
    }
}
