package duke.tasks;

/**
 * Todos are tasks without any date/time attached to it.
 * An todo object is used to store information related to tasks of type 'todo'.
 */
public class Todo extends Task {

    /**
     * Constructor for todo class object.
     *
     * @param description description for the todo to be created.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for todo class object, used for storage.
     *
     * @param description description for the todo to be created.
     * @param isDone if the todo is done.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns the details of the todo.
     *
     * @return the details of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Gets the details of the todo task for storage.
     *
     * @return the details of the todo task to be stored.
     */
    @Override
    public String getTaskInfoToStore() {
        assert !description.isEmpty() : "todo does not have a description!";

        String divider = " | ";
        return "T" + divider
                + (isDone ? "1" : "0") + divider
                + description;
    }
}
