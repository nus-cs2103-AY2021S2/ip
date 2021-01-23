public class Task {
    protected boolean isDone;
    protected final String description;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public void done() {
        isDone = true;
    }

    public String data() {
        String done = isDone ? "1" : "0";
        return String.format("%s | %s", done, description);
    }

    @Override
    public String toString() {
        String output;
        if (isDone) {
            output = String.format("[X] %s", description);
        } else {
            output = String.format("[ ] %s", description);
        }
        return output;
    }
}
