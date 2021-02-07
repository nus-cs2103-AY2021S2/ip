package seashell.task;

public class Task {
    private boolean isDone;
    private String name;

    /**
     * Create a task object with specified name
     * @param name
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    protected Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Set this task as done
     * @return a new task which is marked as done
     */
    public Task setDone() {
        Task doneTask = new Task(this.name, true);
        return doneTask;
    }

    /**
     * Parse this task object into a save string
     * @return string representing task object to be saved in save file
     */
    public String getSaveText() {
        StringBuilder sb = new StringBuilder();
        if (this.isDone) {
            sb.append("1 | ");
        } else {
            sb.append("0 | ");
        }
        sb.append(this.name);
        return sb.toString();
    }

    /**
     * Get icon to signify if task is done
     * @return tick symbol if task is done or cross if task is not done
     */
    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
