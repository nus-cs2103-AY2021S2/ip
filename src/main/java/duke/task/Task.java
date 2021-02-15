package duke.task;

/**
 * Abstract class for tasks. Contains information about the task description and
 * whether is has been carried out.
 */
public abstract class Task {
    private String content;
    private boolean isDone;
    private Priority priority;

    /**
     * Abstract Class constructor.
     * Default priority is set to Medium.
     *
     * @param content description of the task.
     */
    public Task(String content) {
        this.content = content;
        this.priority = Priority.MEDIUM;
        this.isDone = false;
    }

    /**
     * Information needed to be write/read in/to file.
     * In the format of Type|
     *
     * @return String representation in file
     */
    public String toFileString() {
        String done;
        if (this.getDone()) {
            done = "1";
        } else {
            done = "0";
        }
        return done + "|" + priority.getValue() + "|" + this.getDesc();
    }

    /**
     * String representation of whether the task is done.
     * To be used by the concrete implementation of Tasks only.
     *
     * @return [X] content if done [ ] content otherwise.
     */
    public String toString() {
        return String.format("[%s][%s]%s", isDone ? "X" : " ", this.getPriority(), this.getDesc());
    }

    /**
     * Set Task to be done.
     *
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Gets the task description.
     *
     * @return Description of duke.task.Task
     */
    public String getDesc() {
        return this.content;
    }

    /**
     * Gets the status of whether the task is done or not.
     *
     * @return Boolean representation of whether task is done or not.
     */
    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Sets the current task priority to the one stated.
     * @param priority Priority to be set.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Gets the priority the task has.
     * @return priority of the task.
     */
    public Priority getPriority() {
        return priority;
    }
}
