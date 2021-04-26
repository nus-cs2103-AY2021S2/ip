package duke.task;

public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with description.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * get X if the task is done else get space.
     *
     * @return "X" if the task is done else return " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks itself done.
     *
     */
    public void markedAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
