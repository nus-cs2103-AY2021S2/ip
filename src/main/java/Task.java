public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[X] %s", name);
        } else {
            return String.format("[ ] %s", name);
        }
    }

    void markAsDone() {
        this.isDone = true;
    }
}
