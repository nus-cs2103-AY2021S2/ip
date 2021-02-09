package dukeproject;

/**
 * Represents a task with a description and whether the task has been done.
 *
 * X for the isDone parameter means that the event has been completed.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor for the task, specifying the description.
     * By default, the task is not done yet.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for the task, specifying the description and whether the task is done.
     *
     * @param description Description of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Mark the task as done and return a success message from getTask().
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Get the status icon depending on whether the task is done or not.
     *
     * @return Symbol "X" if it is done, else returns an empty string " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return X symbols if done, else return blank space
    }

    /**
     * Returns a message that a task has been successfully added.
     *
     * @param taskListSize Size / Length of the task list.
     * @return A message to describe that the task has been successfully added.
     */
    public String successMessage(int taskListSize) {
        return String.format("Got it. I've added this task:\n"
                + "  %s\n" + "Now you have %d tasks in the list.\n",
                this.toString(), taskListSize);
    }

    /**
     * Returns a message that a task has been successfully deleted.
     *
     * @param taskListSize Size / Length of the task list.
     * @return A message to describe that the task has been successfully deleted.
     */
    public String deleteMessage(int taskListSize) {
        return String.format("Noted. I've removed this task:\n"
                + "  %s\n" + "Now you have %d tasks in the list.\n",
                this.toString(), taskListSize);
    }

    /**Returns a readable description of the task. */
    @Override
    public String toString() {
        return description;
    }
}
