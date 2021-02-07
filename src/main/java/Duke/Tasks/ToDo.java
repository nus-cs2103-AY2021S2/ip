package Duke.Tasks;

/**
 * A subclass of Duke.Tasks.Task that has a description and overrides the toString() method of <code>Duke.Tasks.Task</code>
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
