package duke.task;

/**
 * Class Task which keeps track of the details of the task
 * and whether the task is done.
 */
public class Task {
    private String name;
    private boolean done;

    /**
     * Takes in details and creates a Task object.
     *
     * @param name details of the task
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[X] %s", this.name);
        } else {
            return String.format("[  ] %s", this.name);
        }
    }

    /**
     * Ammends if the task is done.
     */
    public void setDone() {
        this.done = true;
    }

    /**
     * Returns details of the task.
     *
     * @return details of the task
     */
    public String getTaskDetails() {
        return this.name;
    }
}
