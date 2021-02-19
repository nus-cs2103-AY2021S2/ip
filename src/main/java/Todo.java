/**
 * Represents a category of tasks called Todo, that includes the task
 * that has to be completed.
 */
public class Todo extends Task {

    /**
     * Initializes a Todo task object.
     *
     * @param description  refers to the task description.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
