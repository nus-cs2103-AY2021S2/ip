package duke.task;

/**
 * This class handles the TodoTask.
 */
public class TodoTask extends Task {
    /**
     * Constructor of todoTask
     *
     * @param task
     */
    public TodoTask(String task) {
        super(task);
        divideCommand = task.split(" ");
    }

    /**
     * String representation of todoTask
     *
     * @return
     */
    @Override
    public String toString() {
        if (this.isDone()) {
            return "[T][X] " + getName();
        } else {
            return "[T][-] " + getName();
        }
    }
}
