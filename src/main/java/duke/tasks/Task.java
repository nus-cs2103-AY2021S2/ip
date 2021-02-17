package duke.tasks;

/**
 * A task class to represent different types of tasks.
 */
public class Task {
    protected String info;
    protected boolean isDone;
    protected taskType type;

    /**
     * Default constructor used when a new task is added
     *
     * @param info name of the task
     * @param type the taskType for classification
     */
    public Task(String info, taskType type) {
        this.info = info;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Secondary constructor used when a task is loaded from .txt file
     *
     * @param info name of the task
     * @param type the taskType for classification
     * @param isDone boolean value to indicate whether task is done
     */
    public Task(String info, taskType type, boolean isDone) {
        this.info = info;
        this.type = type;
        this.isDone = isDone;
    }

    /**
     * Returns the details of the task such as the task name.
     *
     * @return details of the task.
     */
    public String getInfo() {
        return this.info;
    }

    /**
     * Outputs an icon which is to be used the completion of a task
     * when 'done' command is used on a task.
     *
     * @return an icon which is a cross.
     */
    public String checkIcon() {
        if (isDone) {
            return "\u0058"; // icon is a cross
        } else {
            return " ";
        }
    }

    /**
     * Marks a task object as done.
     */
    public void markCompleted() {
        isDone = true;
    }

    /**
     * Returns the task type.
     *
     * @return enum indicating the type of task.
     */
    public taskType getType() {
            return this.type;
    }

    /**
     * Outputs the details of the task so that the task
     * can be saved into a .txt file.
     *
     * @return String containing details of the task.
     */
    public String toSave() {
        return this.getType().toString() + " " + this.getInfo();
    }

    /**
     * Retuns the completion status of a task
     *
     * @return boolean value indicating whether the task is completed.
     */
    public boolean getDoneStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String output = "[" + checkIcon() + "]" + this.info;
        return output;
    }
}