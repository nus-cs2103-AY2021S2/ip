package duke.tasks;

/**
 * ToDo class that extends from parent Task class.
 * ToDo class specifies a task with a description.
 */
public class ToDo extends Task {
    /**
     * Constructor method
     * @param description The task description.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
