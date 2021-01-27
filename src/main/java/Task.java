public abstract class Task {
    String taskName;
    boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public boolean MarkAsDone() {
        this.done = true;
        return true;
    }

    public boolean MarkAsNotDone() {
        this.done = false;
        return false;
    }

    @Override
    public String toString() {
        return "[" + (done? "x" : " ") + "] " + taskName;
    }

    public abstract String generateDataString();
}
