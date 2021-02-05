package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task with given description.
     * isDone status is automatically set as False;
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a Task with given description and isDone status.
     *
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of task
     *
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }

    public void setDone() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns true if description contains kw, false otherwise
     *
     * @param kw
     * @return boolean
     */
    public boolean contains(String kw) {
        return description.contains(kw);
    }

    public abstract String toSavedString();
}
