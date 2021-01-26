public class Todo extends Task {
    /**
     * Initialises a newly created Todo object
     * so that it represents a Todo task with a name.
     * Initialises the Todo task isDone status to false.
     *
     * @param name the description of the Todo task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Initialises a newly created Todo object
     * so that it represents a Todo task with a name.
     * Initialises the Todo task isDone status to given isDone argument.
     *
     * @param name the description of the Todo task.
     * @param isDone the status of the Todo task.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Creates a String representation of Todo object.
     *
     * @return String representation of Todo.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
