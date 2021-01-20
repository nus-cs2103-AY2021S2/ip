public class Task {
    public String task;
    public boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
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

    public String doneTask() {
        isDone = true;
        return "Good job on completing this task!\n" + toString();
    }
}
