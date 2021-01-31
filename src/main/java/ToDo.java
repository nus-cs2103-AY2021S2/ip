/**
 * Represents a task of type ToDo.
 * A ToDo is represented by a name in the form of a string.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo.
     * @param name Description of Todo.
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
