/**
 * Represents a Task.
 *
 * @author Oh Jun Ming
 * @version 1.0
 */
public class Task {
    protected String msg;
    protected Boolean isDone;

    /**
     * Returns a Task.
     *
     * @param msg description of task.
     * @return Task
     */
    Task(String msg) {
        this.msg = msg;
        this.isDone = false;
    }

    /**
     * Returns a Task.
     *
     * @param msg description of task.
     * @param isDone boolean that tracks whether task is completed.
     * @return Task
     */
    Task(String msg, Boolean isDone) {
        this.msg = msg;
        this.isDone = isDone;
    }

    /**
     * Returns a Task that set boolean isDone as true.
     *
     * @return Task Marks Task as done.
     */
    public Task setDone() {
        return new Task(this.msg, true);
    }

    public String encode() {
        if (isDone.equals(true)) {
            return "DONE" + "|" + this.msg;
        } else {
            return "PENDING" + "|" + this.msg;
        }
    }


    /**
     * Returns a String that describes Task.
     *
     * @return String
     */
    @Override
    public String toString() {
        if (isDone.equals(true)) {
            return "[" + "DONE" + "] " + msg;
        } else {
            return "[ ] " + msg;
        }
    }
}
