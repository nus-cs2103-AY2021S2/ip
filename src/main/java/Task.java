public abstract class Task {
    private String taskName;
    protected boolean done;

    Task(String name){
        this.done = false;
        this.taskName = name;
    }

    /**
     * Mark this task as done
     */
    public void markAsDone(){
        this.done = true;
    }

    /**
     * Get task name
     */
    public String getTaskName(){
        return this.taskName;
    }

    /**
     * Get the status of the task
     */
    public boolean isDone(){
        return this.done;
    }

   abstract String toSaveFormat();
}
