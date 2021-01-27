/**
 * Abstract class that contains the template of a task
 * object.
 */
public abstract class Task {
    protected Boolean isDone;
    protected String taskInfo;

    /**
     * Constructor that creates a task object.
     * @param taskInfo the description of the task.
     */
    public Task(String taskInfo) {
        isDone = false;
        this.taskInfo = taskInfo;
    }

    @Override
    public String toString() {
        String s = "";
        if (isDone) {
            s = "[X] ";
        } else {
            s = "[ ] ";
        }
        return s + taskInfo;
    }

    /**
     * Method that marks the task as done.
     */
    public void completed() {
        isDone = true;
    }

    /**
     * Abstract method that converts the task into a
     * specific format before storing in a file.
     * @return a string of data obtained from the task.
     */
    public abstract String getData();
}
