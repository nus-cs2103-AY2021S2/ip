package duke;

/**
 * Represents a Todo object.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a String representing this object to be saved into a save data file.
     * @return String representation of this object, formatted for save data use.
     */
    public String getSaveString() {
        if (this.isDone()) {
            return String.format("[isDone] todo %s\n", description);
        } else {
            return String.format("todo %s\n", description);
        }
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatus(), description);
    }
}