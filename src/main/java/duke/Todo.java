package duke;

/**
 * Allows users to specify a todo task. The user is able to specify the description.
 */
public class Todo extends Task {

    /**
     * Initialises Todo object.
     *
     * @param description the description of the task.
     */
    public Todo(String description) {
        super(description, "Todo");
    }

    /**
     * Returns a String of the Todo task object in a standardised format.
     *
     * @return a String object of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
