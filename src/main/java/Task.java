public class Task {
    private final String description;
    private boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? '✓' : 'X', description);
    }
}
