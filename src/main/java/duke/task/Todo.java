package duke.task;

public class Todo extends Task {

    /**
     * Creates a new todo task
     * @param name todo name
     */
    public Todo(String name) {
        super(name, "Make sure you do this task!");
    }

    /**
     * Overloads the Todo(String name) method, with new status parameter.
     * This can be used if status is required to be defined.
     * @param name event name
     * @param status event status (done, not done)
     */
    public Todo(String name, Boolean status) {
        this(name);
        this.status = status;
    }

    /**
     * Returns a formatted string required for storing Todo task to .txt file
     * @return formatted string of Task for file
     */
    @Override
    public String toFileString() {
        return "T," + (this.status ? "1" : "0") + "," + this.name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
