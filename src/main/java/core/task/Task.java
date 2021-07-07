package core.task;

import java.io.Serializable;

/**
 * A {@code Task} is the smallest entity and all items in the list are instances of {@code Task}.
 */
public class Task implements Serializable {
    protected String taskDescription;
    protected boolean isDone;
    private final int taskUID;
    private static int UID_CURR;

    static {
        UID_CURR = 1;
    }

    {
        taskUID = UID_CURR++;
    }

    /**
     * Returns the Task unique identifier
     * @return - Task UID
     */
    public int getTaskUid() {
        return taskUID;
    }

    /**
     * Constructs a Task using the description, and whether it is done or not.
     * @param desc - description
     * @param isDone - whether it is already done
     */
    public Task(String desc, boolean isDone) {
        this.taskDescription = desc;
        this.isDone = isDone;
    }

    /**
     * Constructs a Task using the description.
     * @param desc - description
     */
    public Task(String desc) {
        this(desc, false);
    }

    /**
     * Returns the status icon depending on whether the task has been done or not.
     * @return - status icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Sets the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns whether the task has already been done or not.
     * @return - whether the task has been done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the task description.
     * @return - the description
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getTaskDescription();
    }

    /**
     * Checks whether the given word is contained in the taskDescription.
     * @param word - the given word to check
     * @return - true if the word is contained, false otherwise
     */
    public boolean contains(String word) {
        return taskDescription.toLowerCase().contains(word.toLowerCase());
    }

}
