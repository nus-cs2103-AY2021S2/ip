public class Task {
    String description;
    boolean isDone;
    String type;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    void completed() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.isDone ? "[X] " + description : "[ ] " + description;
    }
}
