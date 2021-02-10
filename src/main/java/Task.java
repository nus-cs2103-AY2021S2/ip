/**
 * Abstract class that contains the template of a task
 * object.
 */
public abstract class Task {
    public static final String DELIMITER = "!@#";
    private static final String IS_DONE_TRUE_DISPLAY_ICON = "[X]";
    private static final String IS_DONE_FALSE_DISPLAY_ICON = "[ ]";
    public static final String IS_DONE_TRUE_DATA_ICON = "1";
    protected static final String IS_DONE_FALSE_DATA_ICON = "0";
    protected Boolean isDone;
    protected final String taskInfo;

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
        if (isDone) {
            return IS_DONE_TRUE_DISPLAY_ICON + " " + taskInfo;
        } else {
            return IS_DONE_FALSE_DISPLAY_ICON + " " + taskInfo;
        }
    }

    /**
     * Method that marks the task as done.
     */
    public void completeTask() {
        isDone = true;
    }

    /**
     * Method that checks if the task description contains
     * the given subString.
     *
     * @param subString the subString to search for inside the task
     *                  description.
     * @return true if task description contains subString, false otherwise.
     */
    public boolean contains(String subString) {
        return taskInfo.contains(subString);
    }

    /**
     * Abstract method that converts the task into a
     * specific format before storing in a file.
     * @return a string of data obtained from the task.
     */
	public abstract String getData();
}
