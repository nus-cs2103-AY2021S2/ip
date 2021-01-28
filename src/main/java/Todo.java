/**
 * A Todo type of Task.
 * Inherits from the superclass Task.
 */
public class Todo extends Task {
    /**
     * Constructor to create a Todo Task.
     * @param name Name of the Task.
     * @throws DukeException If the description of this Todo task is empty.
     */
    public Todo(String name) throws DukeException {
        super(name);
        if (name.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Constructor used when retrieving data from hard drive.
     * @param name Name of the Todo.
     * @param done If the Todo is done or not.
     */
    public Todo(String name, boolean done) {
        super(name, done);
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }
}
