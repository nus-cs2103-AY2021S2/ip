package duke.task;

/**
 * The Todo class is a type of Task that represents the abstraction
 * of a todo with a description of what is to be done.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo with a
     * description of what is to be done.
     * @param description The specified description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts a todo to the format to be saved to a file.
     * @return The todo in save format.
     */
    @Override
    public String toSaveFormat() {
        String status = super.isDone ? "1" : "0";
        return String.format("T|%s|%s\n", status, super.description);
    }

    /**
     * Converts a todo to the format to be displayed to the user by the Ui.
     * @return The todo in display format.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
    }
}
