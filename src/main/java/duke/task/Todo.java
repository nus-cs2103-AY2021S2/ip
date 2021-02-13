package duke.task;

/**
 * The Todo class is a type of Task that represents the abstraction
 * of a todo with a description of what is to be done.
 */
public class Todo extends Task {
    public static final String ENCODED_TYPE = "T";
    /**
     * Constructs a new Todo with a
     * description of what is to be done.
     * @param description The specified description.
     */
    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        this(description);
        this.isDone = isDone;
    }

    /**
     * Converts a todo to the format to be saved to a file.
     * @return The todo in save format.
     */
    @Override
    public String encode() {
        assert description != null;
        String status = isDone ? "1" : "0";
        return String.format("%s/%s/%s", ENCODED_TYPE, status, description);
    }

    /**
     * Converts a todo to the format to be displayed to the user by the Ui.
     * @return The todo in display format.
     */
    @Override
    public String toString() {
        assert description != null;
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }
}
