package duke.tasks;

/**
 * Class representing tasks with only descriptions.
 */
public class Todo extends Task{
    private static final String TYPE = "TODO";

    /**
     * Default consturctor
     * @param description description of task.
     */
    public Todo(String description) {
        super(description, TYPE);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof  Todo) {
            return this.description.equals(((Todo) obj).getDescription());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
