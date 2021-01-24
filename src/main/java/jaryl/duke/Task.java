package jaryl.duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : " ");
    }

    public void toggleStatus() {
        this.isDone = !this.isDone;
    }

    public String getType() {
        return this.type;
    }

    public String fileFormat() {
        return this.type + " | " + (isDone ? "1 | " : "0 | ") + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}