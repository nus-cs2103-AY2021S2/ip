public class Task {
    private String description;
    private boolean isDone;
    private String type;
    protected String separator = " | ";

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean getStatus() { return this.isDone; }

    @Override
    public String toString() {
        return this.getStatusIcon() + separator + this.getDescription();
    }
}