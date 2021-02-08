package bob.task;

/**
 * Represents Todo tasks
 */
public class Todo extends Task {

    /**
     * Constructor of Todo tasks
     * @param name Name of the task
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructor of Todo tasks
     * @param name Name of the task
     * @param isDone Status of the task
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Prints out the details of Todo task, including the status and name
     * @return A string representing the Todo task
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[T][X] " + this.name;
        } else {
            return "[T][ ] " + this.name;
        }
    }
}
