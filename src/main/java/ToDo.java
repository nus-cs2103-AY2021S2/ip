/**
 * Simulates a todo task.
 */
public class ToDo extends Task {

    /**
     * Creates a new todo object.
     *
     * @param details Details of the todo task.
     */
    public ToDo(String details) {
        super(details);
    }

    /**
     * Constructor to create a task that is completed.
     *
     * @param details Details of the todo task.
     * @param indicator Denotes that event is completed regardless of boolean value passed.
     */
    public ToDo(String details, boolean indicator) {
        super(details, indicator);
    }

    /**
     * Completes the todo task.
     *
     * @return New completed todo object with the same details.
     */
    public ToDo completeTask() {
        return new ToDo(this.getTask_details(), true);
    }

    /**
     * Returns a String describing the todo task as well as its completion status.
     * @return
     */
    public String taskStatus() {
        if (this.isDone()) {
            return "T 1" + this.getTask_details();
        } else {
            return "T 0" + this.getTask_details();
        }
    }
}
