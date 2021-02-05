package todobeast.tasks;

/**
 * An abstract class that represents a Task within the application.
 */
public abstract class Task {
    protected String taskDescription;
    protected boolean isDone;
    public static final String TASK_DELIMITER = " | ";

    /**
     * Default constructor for new tasks.
     * @param taskDescription the description corresponding to the current task
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        isDone = false;
    }

    /**
     * Constructor for tasks that already have a isDone attribute. This constructor will usually be invoked when
     * tasks are being read from storage.
     * @param taskDescription the description corresponding to the current task
     * @param isDone indicates whether this task has been done
     */
    public Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public boolean containsStringInDesc(String regex) {
        return taskDescription.contains(regex);
    }

    /**
     * Formats the current task for storage in the disk and returns a String representation of it
     * @param delimiter the delimiter used to join the various attributes within the task
     * @return the String that contains the formatted attributes of the current task
     */
    abstract public String formatForStorage(String delimiter);
}
