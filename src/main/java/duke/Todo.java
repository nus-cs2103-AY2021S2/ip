package duke;



/**
 * The Todo class has methods for a Todo object
 * Inherits from the Task.
 */

public class Todo extends Task {

    /**
     * Constructor for new Todo
     *
     * @param description of the new Todo
     */

    public Todo(String description) {
        super(description);
    }

    /**
     * Represents Todo event as a string.
     */

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
