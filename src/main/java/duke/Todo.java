package duke;

/**
 * Class representing a todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for todo with description.
     * @param description A string of description describing the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String encode() {
        return "T|" + super.encode();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}


