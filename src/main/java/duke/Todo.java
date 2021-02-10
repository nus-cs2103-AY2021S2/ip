package duke;

/**
 * This class represents a todo event
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string of the format of task to be saved to text file
     * e.g. T | 1 | running
     *
     * @return String of correctly formatted task
     */
    public String saveToFile() {
        return "T" + super.toString();
    }

    /**
     * Returns a string of the format of task to be printed to console
     * e.g. T | 1 | running
     *
     * @return String of correctly formatted task
     */

    @Override
    public String toString() {
        return "T" + super.toString();
    }
}
