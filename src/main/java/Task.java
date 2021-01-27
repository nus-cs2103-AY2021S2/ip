/**
 * A class representing a common Task object. Consists of the description
 * of the task as well as the state.
 */

public class Task {
    protected final String task;
    protected final boolean isDone;

    /**
     * Constructor of a Task object
     * @param task Task need to be done
     */
    Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
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
        if (this.isDone) {
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
        if (this.isDone){
            tick = "X";
        }
        return "[" + tick + "]" + " " + this.task;
    }
}
