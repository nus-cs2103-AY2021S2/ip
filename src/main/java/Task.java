public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean b) {
        this.description = description;
        this.isDone = b;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public Task changeStatus(Task t) {
        return new Task(t.getDescription(), true);
    }

    public String toString() {
        return "[" + getStatusIcon() + "]" + getDescription();

    }
}