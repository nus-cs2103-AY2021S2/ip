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

    private Todo(String description, boolean isDone, boolean isHighPriority) {
        super(description);
        this.isDone = isDone;
        this.isHighPriority = isHighPriority;
    }

    /**
     * Factory method for creating Todo task.
     *
     * @param description Description of the task
     * @return A todo task
     */
    public static Todo createTodo(String description) {
        return new Todo(description);
    }

    /**
     * Returns String in the form "[TYPE] task".
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
                isHighPriority ? "1" : "0",
                description);
    }

    /**
     * Import data from standardised format.
     *
     * @param args Todo details.
     * @return Todo object.
     */
    protected static Todo importData(String[] args) {
        assert args[1].equals("1") || args[1].equals("0") : "Parser.parseImport() missed an invalid input";
        assert args[2].equals("1") || args[2].equals("0") : "Parser.parseImport() missed an invalid input";

        boolean isDone = args[1].equals("1");
        boolean isHighPriority = args[2].equals("1");
        return new Todo(args[3], isDone, isHighPriority);
    }

    /**
     * Returns a new Todo task marked as done.
     *
     * @return A Todo object.
     */
    @Override
    public Todo markDone() {
        return new Todo(description, true, isHighPriority);
    }

    /**
     * Returns the Todo as high priority;
     *
     * @return High priority Todo.
     */
    @Override
    public Todo setHighPriority() {
        return new Todo(description, isDone, true);
    }

    /**
     * Returns the Todo as low priority;
     *
     * @return low priority Todo.
     */
    @Override
    public Todo setLowPriority() {
        return new Todo(description, isDone, false);
    }
}
