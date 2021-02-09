package duck.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * initialize the task
     *
     * @param description the description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * get status of task
     *
     * @return tick or X symbols
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    /**
     * get description of task
     *
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * get task information
     *
     * @return a string show information of task
     */
    public String getTaskInfo() {
        return getStatusIcon() + getDescription();
    }

    /**
     * get period from now to starting time
     *
     * @return the number of period
     */
    public String getTaskInfoOfFile() {
        return getStatusIcon() + getDescription();
    }

    /**
     * mark task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * get period from now to the setting time
     *
     * @return spac, wait for overriding
     */
    public String getPeriodDays() {
        return "";
    }

}
