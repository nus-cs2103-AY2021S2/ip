import java.util.List;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    private static final String TYPE = "T";

    /**
     * Factory method for creating Todo task.
     * @param description Description of the task
     * @return A todo task
     * @throws DukeException if description is empty
     */
    public static Todo createTodo(String description) throws DukeException {
        if (description.length() == 0) {
                throw new DukeException("The description of Todo cannot be empty.");
        }
        return new Todo(description);
    }

    private Todo(String description) {
        super(description);
    }

    private Todo(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
    }
    
    @Override
    public String toString() {
        return String.format("[%s]%s", TYPE, super.toString());
    }

    @Override
    public List<String> exportData() {
        return List.of(TYPE,
                isDone ? "1" : "0",
                description);
    }

    public static Todo importData(String[] args) {
        boolean isDone = args[1] == "1";
        return new Todo(isDone, args[2]);
    }
}
