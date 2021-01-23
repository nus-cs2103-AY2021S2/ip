package duke.task;

/**
 * One kind of Task that is not with date.
 */
public class ToDo extends Task {

    /**
     * Creates a todo task with description
     *
     * @param description description of todo
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
