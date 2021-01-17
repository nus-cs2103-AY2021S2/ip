public class Task {
    String description;
    boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    void completed() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.isDone ? "[X] " + description : "[ ] " + description;
    }
}
