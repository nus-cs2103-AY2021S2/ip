public class Task {
    protected boolean isDone;
    protected String name;

    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    public Task(String name, Boolean isDone) {
        this.isDone = isDone;
        this.name = name;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + name;
    }
}
