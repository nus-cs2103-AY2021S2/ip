public class Task {
    /**
     * Stores the description of this Task.
     */
    protected String description;
    /**
     * Represents whether this Task is done.
     */
    protected boolean isDone;

    /**
     * Initializes a newly created Task object with a description.
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks whether the task is done or not.
     * @return String representing a tick if the task is done, else a String of a space
     */
    public String getStatusIcon() {
        if (isDone) {
            return ("\u2713"); //return tick
        } else {
            return (" ");
        }
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Converts this object to a string that represents the task
     * @return A string representing whether the task is done and the task description
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " +  this.description;
    }
}
