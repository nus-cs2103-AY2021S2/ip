public class Task {

    String description;
    boolean isDone;

    Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[\u2713] " : "[ ] ";
    }

    String getDate() {
        return "";
    }

    public void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
