/**
 * A subclass of Task that has a description and overrides the toString() method of <code>Task</code>
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
