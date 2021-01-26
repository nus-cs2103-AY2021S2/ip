/**
 * Represents tasks the user wants to keep track of.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String save() {
        return toString();
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}
