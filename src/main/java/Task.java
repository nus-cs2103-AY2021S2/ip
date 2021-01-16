public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick
    }

    public String getDescription() {
        return String.format("%s", description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

}
