package duke;

public class Task extends AbstractTask {

    /*
     * Constructs a task using the description
     */
    public Task(String description) throws DukeEmptyDescriptionException {
        super(description);
    }

    /*
     * Return a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("   %s", super.toString());
    }
}