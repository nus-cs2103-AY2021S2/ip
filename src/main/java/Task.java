public class Task {
    protected String description;
    public boolean isDone;
    /**
     * Constructor method
     * @param description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Method to return the corresponding String depending on completion of Task
     * @return Formatted String of Task completion
     */
    public String getStatusIcon() {
        if (isDone) {
            return "[Done]";
        } else {
            return "[Incompleted]";// unsure why tick and cross did not show as intended
            // hence the change to Done and Incompleted.
        }
    }
    /**
     * Method to return the description of the Task
     * @return description String
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Method to mark a Task as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Method to return a Task object in the specified format
     * @return Formatted String of Task
     */
    public String toString() {
        return this.getStatusIcon()  + this.description;
    }
}
