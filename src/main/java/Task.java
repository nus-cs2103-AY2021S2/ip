/**
 * Represents a task
 */
public class Task {
    protected String name;
    protected boolean done = false;

    /**
     * Constructor of a task
     * @param name Name of task
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Constructor of a task
     * @param name Name of task
     * @param done Status of task
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Updates the status of the task
     * @param done The boolean value representing the status of the task
     */
    public void status(boolean done) {
        this.done = done;
    }

    /**
     * Prints the details of a task, including the status and name.
     * @return A string representing a task.
     */
    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
