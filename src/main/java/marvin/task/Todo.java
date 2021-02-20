package marvin.task;

/**
 * Represents a to-do.
 */
public class Todo extends Task {
    public static final String ENCODED_TYPE = "T";

    /**
     * Constructor takes in the description of a to-do.
     * @param description The description of the to-do.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor takes in whether a to-do is done, and its description.
     * @param isDone True if the to-do is done, false otherwise.
     * @param description The to-do of the deadline.
     */
    public Todo(boolean isDone, String description) {
        this(description);
        this.isDone = isDone;
    }

    /**
     * Checks if a task is the same as the task to check with.
     * @param toCheck The task to check with
     * @return True if the tasks are the same, false otherwise.
     */
    @Override
    public boolean isSameTask(Task toCheck) {
        if (toCheck instanceof Todo) {
            return description.equals(toCheck.description);
        }
        return false;
    }

    /**
     * Encodes a to-do into a decodable string representation of the to-do.
     * @return The encoded string representation of the to-do.
     */
    @Override
    public String encode() {
        assert description != null;
        String status = isDone ? "1" : "0";
        return String.format("%s/%s/%s", ENCODED_TYPE, status, description);
    }

    /**
     * Converts a to-do to the format to be displayed to the user by the Ui.
     * @return The to-do in display format.
     */
    @Override
    public String toString() {
        assert description != null;
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }
}
