package Duke.Tasks;

/**
 * Represents the tasks that the user has input into the program. The task has a description and a boolean to
 * indicate that it is completed
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected Priority priority;

    public Task(Priority priority, String description) {
        this.description = description;
        this.isDone = false;
        this.priority = priority;
    }

    /**
     * Gets the status of the <code>Duke.Tasks.Task</code>> to determine if it is done.
     *
     * @return a String of a Tick symbol if it is done and a Cross symbol if it is not
     */
    public String getStatusIcon() {
        return (isDone ? "O" : "X"); //return Y or X symbols
    }

    /**
     * Marks the <code>Duke.Tasks.Task</code> as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the priority of the Task
     *
     * @return A string of a letter either H, M or L that represents the priority. X if no priority
     */
    public String getPriority() {
        switch (priority) {
        case LOW:
            return "L";
        case MEDIUM:
            return "M";
        case HIGH:
            return "H";
        default:
            return "X";
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + "[" + this.getPriority() + "] " + this.description;
    }
}
