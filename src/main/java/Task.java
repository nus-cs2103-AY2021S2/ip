public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    /**
     * Mark task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Get status icon representing done state.
     *
     * @return Status icon
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Convert to file string for saving.
     *
     * @return File string
     */
    public String toFileString() {
        return String.format("%s|%b|%s", "X", isDone, desc);
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + desc;
    }
}
