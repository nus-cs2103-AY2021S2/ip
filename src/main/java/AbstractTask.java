public abstract class AbstractTask {
    protected String description;
    protected boolean isDone;

    public AbstractTask(String description) throws DukeEmptyDescriptionException {
        if (description.isEmpty()) {
            throw new DukeEmptyDescriptionException();
        }
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }
}