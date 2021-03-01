/**
 * The Task class is a parent class for ToDo, Deadline and Event
 * whereby each Task has a boolean to indicate whether it has been done
 */
class Task {
    protected String description;
    protected boolean isDone;

    /**
     * The Task applies to inputs with sufficient information to classify as a todo, deadline
     * or event task.
     * @param description describes the details of a task supplied to the chat bot
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * For a given task, indicate whether it has been done using a space or a tick
     * @return if the task has been done, return a tick, else return a space
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick symbol
    }

    /**
     * For a given task, if it is done, mark it as done by changing the boolean isDone to true
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
