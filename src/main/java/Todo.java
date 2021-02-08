/** Class Todo that represent a Todo task **/
public class Todo extends Task {
    /** Symbol to represent a Todo task **/
    private static final char SYMBOL = 'T';

    /**
     * Constructor of a Event task.
     * @param desc Description of the deadline task
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * Returns the representation of a task.
     * @return String representation of a task
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", SYMBOL, super.toString());
    }

    /**
     * Returns the representation of a task for saving.
     * @return String representation of a task for saving
     */
    @Override
    public String save() {
        return String.format("%c,%s\n", SYMBOL, super.save());
    }
}
