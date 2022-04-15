package duke.task;

public class Task {
    protected static final String DATE_DISPLAY_FORMAT = "MMM d yyyy";
    protected static final String DATE_SAVE_FORMAT = "yyyy-MM-dd";
    private String name;
    private boolean isDone;

    /**
     * Creates a new Task.
     * @param name
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Returns tick or X symbols.
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
