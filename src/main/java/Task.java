public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
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

    public String getType() {
        return type;
    }

}
