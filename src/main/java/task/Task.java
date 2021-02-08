package task;

public abstract class Task {
    protected String taskDescription;
    protected boolean isDone;
    protected char taskType;

    /**
     * Gets the task description
     *
     * @return String description of the task
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Checks if task has been marked completed
     *
     * @return value of isDone in task object
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Mark task as completed
     */
    public void completeTask() {
        this.isDone = true;
    }

    @Override
    public boolean equals(Object t){
        if (!(t instanceof Task)) {
            return false;
        }
        return this.toString().equals(t.toString());
    }
}
