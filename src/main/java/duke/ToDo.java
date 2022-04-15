package duke;

public class ToDo extends Task {
    public static final String SHORT_HAND = "T";

    /**
     * Constructs a ToDo task.
     * @param taskDescription description of the ToDo task.
     */
    public ToDo (String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        return "[" + SHORT_HAND + "]" + super.toString();
    }
}
