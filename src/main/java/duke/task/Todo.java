package duke.task;

/**
 * Representation of most basic task with just a task description
 */
public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    public Todo(boolean done, String task) {
        super(task);
        this.isDone = done;
    }

    /**
     * Returns a String representation of this Todo for storage to a file.
     * @return String representation
     */
    public String toFileString() {
        return "T | " + this.isDone + " | " + this.task;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
