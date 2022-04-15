import java.time.LocalDate;

/**
 * Represents a task.
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructor takes in one parameter, <code>description</code>.
     * @param description a description of the task
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Generates the text of this task in the proper format to be stored
     * into the text file at the end of execution.
     * @return A <code>String</code> text in the proper format to be stored
     * into the text file
     */
    public String generateText() {
        return this.description;
    }

    public LocalDate getDate() {
        return null;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the description of the task.
     * @return the description of task
     */
    public String getDescription() {
        return this.description;
    }

    public Task reschedule(String fullCommand) throws DukeException {
        return this;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
