package duke;

import java.io.Serializable;

public abstract class AbstractTask implements Serializable {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a task using the description
     *
     * @throws DukeEmptyDescriptionException if the description is empty
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
