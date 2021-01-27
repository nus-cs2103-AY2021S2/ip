/**
 * Represents a Todo.
 * Sub-class of Task.
 *
 * @author Oh Jun Ming
 * @version 1.0
 */
public class Todo extends Task{
    /**
     * Returns a Todo.
     *
     * @param msg description of Todo.
     * @return Todo
     */
    Todo(String msg) {
        super(msg);
    }

    /**
     * Returns a Todo.
     *
     * @param msg description of Todo.
     * @param isDone boolean that tracks whether Todo is completed.
     * @return Todo
     */
    Todo(String msg, Boolean isDone) {
        super(msg, isDone);
    }

    /**
     * Returns a Todo object that set boolean isDone as true.
     *
     * @return Todo Marks Todo as done.
     */
    @Override
    public Todo setDone() {
        return new Todo(this.msg, true);
    }

    /**
     * Returns a String that describes Todo.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
