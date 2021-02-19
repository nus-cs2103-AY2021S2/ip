package duke;

/**
 * Provides super class for types of tasks.
 */
public class Task {
    private String description;
    private boolean isDone;
    private String taskType;

    /**
     * Initialises Task objects.
     *
     * @param description the description of the task.
     * @param taskType the string specifying task type.
     */
    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Returns String specifying if task is done or not.
     *
     * @return String object in the form of a tick if the task is done.
     */
    public String getStatus() {
        return (isDone ? "\u2713" : " "); //return tick if done
    }

    /**
     * Returns boolean specifying if task is done or not.
     *
     * @return boolean of if task is done or not.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns String specifying task type.
     *
     * @return String object of task type.
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * Returns String specifying description of the task.
     *
     * @return String object of task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates description of the task.
     *
     * @param newDescription new description user wants to edit.
     */
    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Returns a String of the Event task object in a standardised format.
     *
     * @return a String object of the Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + description;
    }
}
