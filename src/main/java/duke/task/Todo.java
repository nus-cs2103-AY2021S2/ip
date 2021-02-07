package duke.task;

public class Todo extends Task {

    /**
     * Initializes a todo item with its description
     *
     * @param description Description of the todo item
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the todo to a string which will be saved in a file.
     *
     * @return String representing the todo in its save format.
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Converts the todo to a string.
     *
     * @return String representing the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
