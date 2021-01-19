public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String checkStatus() {
        return (isDone ? "\u2713" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + checkStatus() + "] " + description;
    }
}
