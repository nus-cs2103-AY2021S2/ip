public class Task {
    private boolean done;
    private final String description;

    public Task(String description) {
        this.description = description;
        done = false;
    }

    public void done() {
        done = true;
    }

    @Override
    public String toString() {
        String output;
        if (done) {
            output = String.format("[X] %s", description);
        } else {
            output = String.format("[ ] %s", description);
        }
        return output;
    }
}
