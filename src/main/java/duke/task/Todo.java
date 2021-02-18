package duke.task;

/**
 * A Todo type of task.
 */
public class Todo extends Task {

    /**
     * Constructor to initialise a todo task with a description
     * @param description the decription of the task
     */
    public Todo(String description) {
        super(description);
        this.type = "todo";
    }

    /**
     * Overridden toString() method which includes the type of the task
     * @return string with details of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
