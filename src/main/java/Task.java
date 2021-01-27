import java.io.Serializable;

public class Task implements Serializable {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : " ");
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
