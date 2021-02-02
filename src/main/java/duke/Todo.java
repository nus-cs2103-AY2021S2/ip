package duke;

public class Todo extends AbstractTask {

    /*
     * Constructs a task using the description
     */
    public Todo(String description) throws DukeEmptyDescriptionException {
        super(description);
    }

    /*
     * Return a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
