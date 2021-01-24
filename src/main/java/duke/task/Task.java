package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2718" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toSaveFormat() {
        return getStatusIcon() + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}