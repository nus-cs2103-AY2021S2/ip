public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Marks the task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of how the task will be stored in the file.
     *
     * @return String
     */
    public String toFileString() {
        char done = this.isDone ? 'X' : ' ';
        return String.format("%c | %s", done, this.description);
    }

    public String toString() {
        char done = this.isDone ? 'X' : ' ';
        return String.format("[%c] %s", done, this.description);
    }
}
