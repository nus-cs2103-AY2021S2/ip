package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
            this.description = description;
            this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
}

  
    /** 
     * Returns a string representation of task
     * 
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }

    public void setDone() {
        isDone = true;
    }

    public abstract String toSavedString();
}