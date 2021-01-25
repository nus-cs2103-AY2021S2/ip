/**
 * Represents a task listed in Duke and can be marked as done.
 */
public class Task {
    protected boolean isDone;
    protected String description;

    /**
     * Creates a task with the specified description.
     *
     * @param description The task's description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String symbol = " ";
        if (isDone) {
            symbol = "X";
        }
        return "[" + symbol + "] " + this.description;
    }
}
