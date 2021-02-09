package vergil.types;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        assert description != null : "Description cannot be null.";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Construct a new task object.
     * @param description a description of the task.
     */
    public Task(String description, boolean isDone) {
        assert description != null : "Description cannot be null.";
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the task's description.
     * @return the task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the task's current state.
     * @return true if the task is done; false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Changes the task's state to done.
     */
    public void doTask() {
        isDone = true;
    }

    public String getSaveString() {
        return String.format("%s::%b", description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }
}
