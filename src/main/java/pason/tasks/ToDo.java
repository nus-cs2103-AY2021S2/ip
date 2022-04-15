package pason.tasks;

/**
 * Initialises the ToDo Event.
 */
public class ToDo extends Task {
    /**
     * Initialises a ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Formats Event into text format for file saving.
     */
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
