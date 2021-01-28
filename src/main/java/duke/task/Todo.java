package duke.task;

/**
 * A class represents a Todo.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo.
     * @param name The name of the Todo task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns a string in the given format.
     * @return A string in the given format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string in the given format for storing in files.
     * @return A string in the given format for storing in files.
     */
    @Override
    public String toFileString() {
        return "T " + super.toFileString();
    }
}
