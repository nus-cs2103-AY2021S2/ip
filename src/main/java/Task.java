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

    /**
     * Returns a String representation of whether this Task is done.
     * @return "X" if the Task is done, " " (one space) otherwise.
     */
    protected String getStatus() {
        if (this.done) {
            return "X";
        } else {
            return " ";
        }
    }
}
