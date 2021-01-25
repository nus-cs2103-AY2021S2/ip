package mike.task;

/**
 * A Task with no time limit
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo Task with description
     *
     * @param description a description of the Task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
