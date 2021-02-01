/**
 * Representation of a ToDo task. Inherits from Task.
 */
public class ToDo extends Task {
    /**
     * Class constructor. Creates a not done ToDo object with name set to the specified taskName.
     * @param taskName The specified name of the new ToDo object.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * A method to create a neatly formatted String that describes this ToDo.
     * @return Neatly formatted String representation of this ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Generates a formatted String for storage read and write purposes.
     * @return Formatted data String to be used by Storage.
     */
    @Override
    public String generateDataString() {
        return "todo " + (done ? "done " : "notDone ") + taskName;
    }
}
