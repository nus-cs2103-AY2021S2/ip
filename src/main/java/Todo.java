/**
 * A class represents a Todo event. Consists of task description and state
 * of the task
 */
public class Todo extends Task {

    /**
     * Constructor of Todo object
     * @param task Description of the task
     */
    Todo (String task) {
        super(task);
    }

    /**
     * Constructor of Todo object
     * @param task Description of the task
     * @param done State of the task
     */
    Todo (String task, boolean done) {
        super(task, done);
    }

    /**
     * An overridden method from the parent class Task. The purpose is to
     * return a new finished Task.
     * @return Finished Todo object.
     */
    @Override
    public Task finishTask() throws DukeException {
        if (this.isDone) {
            throw new DukeException("Task has been done before!");
        }
        return new Todo(this.task, true);
    }

    /**
     * An overridden method from the parent class Task. The purpose is to
     * return a String representation for txt files
     * @return String for txt files
     */
    @Override
    public String saveString() {
        return "T|" + super.saveString();
    }

    /**
     * Return a string representation of Todo object
     * @return String representation of Todo object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
