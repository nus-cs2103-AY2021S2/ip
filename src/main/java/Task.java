/**
 * A class representing a common Task object. Consists of the description
 * of the task as well as the state.
 */

public class Task {
    protected final String task;
    protected final boolean done;

    /**
     * Constructor of a Task object
     * @param task Task need to be done
     */
    Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Constructor of a Task object
     * @param task Task need to be done
     * @param done State of the task
     */
    Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    /**
     * Finish the current Task
     * @return New Task object that has been done
     */
    public Task finishTask() {
        return new Task(this.task, true);
    }

    /**
     * Return a String representation for saving in txt files
     * @return String representation in txt files
     */
    public String saveString() {
        if (this.done) {
            return "1|" + this.task;
        }
        return "0|" + this.task;
    }

    /**
     * String representation of Task object
     * @return String representation of Task object
     */
    @Override
    public String toString() {
        String tick = " ";
        if (this.done){
            tick = "X";
        }
        return "[" + tick + "]" + " " + this.task;
    }
}
