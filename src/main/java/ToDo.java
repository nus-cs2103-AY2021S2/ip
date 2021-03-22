/**
 * A Todo type of Task.
 * Inherits from the superclass Task.
 */

public class ToDo extends Task{

    protected char type;

    /**
     * Constructor to create a ToDo Task.
     * @param task Name of the ToDo Task.
     */
    public ToDo(String task) {
        super(task);
        this.type = 'T';
    }

    /**
     * Constructor used when retrieving data from hard drive.
     * @param task Name of the Todo Task.
     * @param isDone If the Todo Task is done or not.
     */
    public ToDo(String task, boolean isDone) {
        super(task, isDone);
        this.type = 'T';
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }

    @Override
    public String getData() {
        return "[" + type + "]" + super.toString();
    }
}
