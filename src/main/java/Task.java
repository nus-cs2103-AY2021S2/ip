public abstract class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false; // tasks always start as not done
    }

    public boolean isDone() { return isDone; }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String getSaveString();

    protected String getStatus() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }
}
