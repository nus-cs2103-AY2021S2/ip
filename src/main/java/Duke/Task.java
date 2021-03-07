package Duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String dateTime;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    public void markDone() {
        this.isDone = true;
    }
    public String getDoneStatus() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public String encode() {
        if (isDone) {
            return "X|" + this.description ;
        } else {
            return " |" + this.description ;
        }
    }

    public String toString() {
        return "[" + getDoneStatus() + "]" + getDescription();

    }
}