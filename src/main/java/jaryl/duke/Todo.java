package jaryl.duke;

/**
 * Todo represents a task with a task description
 */
public class Todo extends Task {
    /**
     * Constructor to instantiate a new Todo task
     * @param description               description of task
     * @throws InvalidFormatException   invalid format exception
     */
    public Todo(String description) throws InvalidFormatException {
        super(description, "T");
        if(description.equals("")) {
            throw new InvalidFormatException("Please specify task description");
        }
    }

    /**
     * Converts Todo task to string format
     * @return Todo task in original String format
     */
    @Override
    public String toString() {
        return "[" + super.getType() + "]" + super.toString();
    }
}
