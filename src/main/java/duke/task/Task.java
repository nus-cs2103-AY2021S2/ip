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
            String toPrint = String.format("[X] %s", this.name);
            assert !toPrint.isEmpty() : "Something should be printed.";
            return toPrint;
        } else {
            String toPrint = String.format("[  ] %s", this.name);
            assert !toPrint.isEmpty() : "Something should be printed.";
            return toPrint;
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

    public boolean isEmpty() {
        return false;
    }
}
