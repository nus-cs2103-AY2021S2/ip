public abstract class AbstractTask {
    protected String description;
    protected boolean isDone;

    /*
     * Constructs a task using the description
     */
    public AbstractTask(String description) throws DukeEmptyDescriptionException {
        if (description.isEmpty()) {
            throw new DukeEmptyDescriptionException();
        }
        this.description = description;
        this.isDone = false;
    }

    /*
     * Mark the task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /*
     * Return a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }
}