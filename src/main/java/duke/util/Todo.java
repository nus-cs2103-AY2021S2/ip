package duke.util;

import java.util.List;

/**
 * Represents a Todo task.
 * <br>A todo task has a description.
 */
public class Todo extends Task {

    private static final String TYPE = "T";

    private Todo(String description) {
        super(description);
    }

    private Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Factory method for creating Todo task.
     *
     * @param description Description of the task
     * @return A todo task
     * @throws DukeInputException if description is empty
     */
    public static Todo createTodo(String description) throws DukeInputException {
        if (description.length() == 0) {
            throw new DukeInputException("The description of Todo cannot be empty.");
        }
        return new Todo(description);
    }

    /**
     * Returns String in the form "[Type] task".
     *
     * @return String representation of Todo.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", TYPE, super.toString());
    }

    /**
     * Export data into a standardised format.
     *
     * @return List of Todo details.
     */
    @Override
    protected List<String> exportData() {
        return List.of(TYPE,
                isDone ? "1" : "0",
                description);
    }

    /**
     * Import data from standardised format.
     *
     * @param args Todo details.
     * @return Todo object.
     */
    protected static Todo importData(String[] args) {
        assert args[1].equals("1") || args[1].equals("0") : "Parser.checkImportFormat() missed an invalid input";

        boolean isDone = args[1].equals("1");
        return new Todo(args[2], isDone);
    }

    /**
     * Returns a new Todo task marked as done.
     *
     * @return A Todo object.
     */
    @Override
    public Todo markDone() {
        return new Todo(description, true);
    }
}
