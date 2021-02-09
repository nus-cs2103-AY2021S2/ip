package duke.tasks;

/**
 * Inherited from Task, used to store information related to tasks of type 'todo'.
 *
 * Todos are tasks without any date/time attached to it.
 */
public class Todo extends Task {

    /**
     * Constructor for todo class object
     * @param description todo description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for todo class object, used for storage
     * @param description todo description
     * @param isDone if the todo is done
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Acts as a helper method to print out the details of the todo
     * @return the details of the todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Helper method to get the details of the todo task to be stored
     * @returnthe the details of the todo task to be stored
     */
    @Override
    public String infoToStore() {
        assert !description.isEmpty() : "todo does not have a description!";

        String divider = " | ";
        return "T" + divider
                + (isDone ? "1" : "0") + divider
                + description;
    }
}
