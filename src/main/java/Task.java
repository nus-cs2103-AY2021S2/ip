public abstract class Task {
    String taskName;
    boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsNotDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        return "[" + (done? "x" : " ") + "] " + taskName;
    }

    public abstract String generateDataString();
}
