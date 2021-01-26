public abstract class Task {
    protected final String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false; // tasks always start as not done
    }

    public boolean isDone() { return done; }

    public void finish() {
        this.done = true;
    }

    public abstract String getSaveString();

    protected String getStatus() {
        if (this.done) {
            return "X";
        } else {
            return " ";
        }
    }
}
