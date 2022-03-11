package todobeast.tasks;

import todobeast.commands.TaskType;

/**
 * An abstract class that represents a Task within the application.
 */
public abstract class Task {
    public static final String TASK_DELIMITER = " | ";

    protected TaskType taskType;
    protected String taskDescription;
    protected boolean isDone;
    protected String taskNotes;

    /**
     * Constructor that allows user to add notes to the task being created.
     * @param taskDescription the description corresponding to the current task
     * @param isDone indicates whether this task has been done
     * @param taskNotes any additional notes for the task being created
     */
    public Task(TaskType taskType, String taskDescription, boolean isDone, String taskNotes) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.isDone = isDone;
        this.taskNotes = taskNotes;
    }

    public void setDone() {
        isDone = true;
    }

    public void setTaskNotes(String taskNotes) {
        this.taskNotes = taskNotes;
    }

    public boolean hasTaskNotes() {
        return taskNotes != null;
    }

    /**
     * Returns true if the regex provided is found within the task description of this particular task.
     * @param regex the regex used to match
     * @return true if the regex is found within the task description, false otherwise
     */
    public boolean containsStringInDesc(String regex) {
        return taskDescription.contains(regex);
    }

    /**
     * Formats the current task for storage in the disk and returns a String representation of it
     * @param delimiter the delimiter used to join the various attributes within the task
     * @return the String that contains the formatted attributes of the current task
     */
    public abstract String formatForStorage(String delimiter);

    public void clearTaskNotes() {
        taskNotes = null;
    };
}
