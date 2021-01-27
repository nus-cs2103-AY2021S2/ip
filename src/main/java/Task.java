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
     * @param isDone Whether or not the task is done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Checks whether the task is done or not.
     * @return String representing a tick if the task is done, else a String of a space
     */
    protected String getStatusIcon() {
        if (isDone) {
            return ("\u2713"); //return tick
        } else {
            return (" ");
        }
    }

    /**
     * Marks the task as done.
     */
    protected void markAsDone() {
        this.isDone = true;
    }

    protected String saveTask() {
        int done = isDone ? 1 : 0;
        return done + " | " + this.description;
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
