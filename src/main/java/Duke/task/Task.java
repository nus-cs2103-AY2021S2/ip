package duke.task;

/**
 * Represents a Task.
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
     * @param task
     * @return boolean
     */
    public boolean equals(Task task) {
        boolean a = this.isDone.equals(task.getDone());
        boolean b = this.msg.equals(task.getMsg());
        return a && b;
    }

    /**
     * Returns a Task that set boolean isDone as true.
     *
     * @return Task Marks Task as done.
     */
    public Task setDone() {
        System.out.println("task set done");
        return new Task(this.msg, true);
    }

    /**
     * Gets Message description.
     *
     * @return String
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Gets Boolean of task completion status.
     *
     * @return Boolean
     */
    public Boolean getDone() {
        return isDone;
    }
    /**
     * Encodes Task to string format for storage.
     *
     * @return String
     */
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
