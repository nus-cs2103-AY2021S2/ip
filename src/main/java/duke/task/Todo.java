package duke.task;

/**
 * Encapsulates information and state of a Todo.
 * For tasks with no location and timing.
 */
public class Todo extends Task {

    /**
     * Initialises a new Todo with text description.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), super.toString());
    }
}
